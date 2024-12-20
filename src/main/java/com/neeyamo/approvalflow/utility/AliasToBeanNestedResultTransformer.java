package com.neeyamo.approvalflow.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;
import org.hibernate.transform.ResultTransformer;

public class AliasToBeanNestedResultTransformer extends AliasedTupleSubsetResultTransformer

{

    private static final long serialVersionUID = -8047276133980128266L;

    private final Class<?> resultClass;

    @Override

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength)

    {

        return false;

    }

    public AliasToBeanNestedResultTransformer(Class<?> resultClass)

    {

        this.resultClass = resultClass;

    }

    @Override

    @SuppressWarnings("unchecked")

    public Object transformTuple(Object[] tuple, String[] aliases)

    {

        Map<String, List<?>> subclassToAlias = new HashMap<>();

        List<String> nestedAliases = new ArrayList<>();

        List<String> nonNestedAliases = new ArrayList<>();

        try

        {

            for (int i = 0; i < aliases.length; i++)

            {

                String alias = aliases[i];

                if (alias.contains("."))

                {

                    nestedAliases.add(alias);

                    String[] sp = alias.split("\\.", 2);

                    String fieldName = sp[0];

                    String aliasName = sp[1];

                    Class<?> subclass = resultClass.getDeclaredField(fieldName).getType();

                    if (!subclassToAlias.containsKey(fieldName))

                    {

                        List<Object> list = new ArrayList<>();

                        list.add(new ArrayList<Object>());

                        list.add(new ArrayList<String>());

                        list.add(subclass);

                        subclassToAlias.put(fieldName, list);

                    }

                    ((List<Object>) subclassToAlias.get(fieldName).get(0)).add(tuple[i]);

                    ((List<String>) subclassToAlias.get(fieldName).get(1)).add(aliasName);

                }

                else

                {

                    nonNestedAliases.add(alias);

                }

            }

        }

        catch (NoSuchFieldException e)

        {

            throw new HibernateException("Could not instantiate resultclass: "

                    + resultClass.getName());

        }

        Object[] newTuple = new Object[nonNestedAliases.size() + subclassToAlias.size()];

        String[] newAliases = new String[nonNestedAliases.size() + subclassToAlias.size()];

        int i = 0;

        for (int j = 0; j < aliases.length; j++)

        {

            if (!nestedAliases.contains(aliases[j]))

            {

                newTuple[i] = tuple[j];

                newAliases[i] = aliases[j];

                ++i;

            }

        }

        // Class<?>

        for ( Map.Entry<String,List<?>> field : subclassToAlias.entrySet())

        {

            ResultTransformer subclassTransformer =

                    new AliasToBeanNestedResultTransformer((Class<?>) subclassToAlias.get(field.getKey())

                            .get(2));

            Object subObject =

                    subclassTransformer.transformTuple(((List<Object>) subclassToAlias.get(field.getKey())

                            .get(0)).toArray(), ((List<Object>) subclassToAlias.get(field.getKey()).get(1))

                            .toArray(new String[0]));

            newTuple[i] = subObject;

            newAliases[i] = field.getKey();

            ++i;

        }

        ResultTransformer rootTransformer = new AliasToBeanResultTransformer(resultClass);

        return rootTransformer.transformTuple(newTuple, newAliases);

    }


}
