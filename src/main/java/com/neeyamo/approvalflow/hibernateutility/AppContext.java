
package com.neeyamo.approvalflow.hibernateutility;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppContext
{

    private static ApplicationContext ctx;

    private AppContext()
    {

        super();
    }

    /**
     * Injected from the class "ApplicationContextProvider" which is automatically loaded during
     * Spring-Initialization.
     */
    public static void setApplicationContext(ApplicationContext applicationContext)
    {

        ctx = applicationContext;
    }

    /**
     * Get access to the Spring ApplicationContext from everywhere in your Application.
     *
     * @return
     */
    public static ApplicationContext getApplicationContext()
    {

        return ctx;
    }
}