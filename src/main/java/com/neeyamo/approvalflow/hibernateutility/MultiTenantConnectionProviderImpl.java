
package com.neeyamo.approvalflow.hibernateutility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider
{
	
	transient DataSource dataSource;
    private static Logger logger = LoggerFactory.getLogger(MultiTenantConnectionProviderImpl.class.getName());

    public MultiTenantConnectionProviderImpl() {

		super();

	} 
    @Override
    public Connection getAnyConnection() throws SQLException
    {

        ApplicationContext context = ApplicationContextProvider.getApplicationContext();

        this.dataSource = (DataSource) context.getBean("multiRoutingDataSource");
        logger.info("inside MultiTenantConnectionProvider::getAnyConnection");
        return dataSource.getConnection();
    }

    public void releaseAnyConnection(Connection connection) throws SQLException
    {
    	  logger.info("inside MultiTenantConnectionProvider::releaseAnyConnection");
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifie) throws SQLException
    {

        String tenantIdentifier = TenantContext.getCurrentTenant();
        final Connection connection = getAnyConnection();
        try (Statement st = connection.createStatement())
        {

            if (tenantIdentifier != null)
            {
                String message = "USE " + tenantIdentifier;
                st.execute(message);
            }
            else
            {
                String message1 = "USE " + TenantIdentifierResolver.DEFAULT_TENANT_ID;
                st.execute(message1);
            }
            logger.info(tenantIdentifier);
            return connection;
        }
        catch (SQLException e)
        {
        	releaseAnyConnection(connection);
            throw new HibernateException("Problem setting schema to " + tenantIdentifier);
        }
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection)
            throws SQLException
    {
    	  try (Statement st = connection.createStatement())
          {
              String message2 = "USE " + TenantIdentifierResolver.DEFAULT_TENANT_ID;
              st.execute(message2);
          }
          catch (SQLException e)
          {
              throw new HibernateException("Problem setting schema to " + tenantIdentifier);
          }
          finally
          {
              connection.close();
          }

    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class unwrapType)
    {

        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType)
    {

        return null;
    }

    @Override
    public boolean supportsAggressiveRelease()
    {

        return true;
    }
}
