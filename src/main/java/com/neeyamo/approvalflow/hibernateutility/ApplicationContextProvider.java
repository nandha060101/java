
package com.neeyamo.approvalflow.hibernateutility;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware
{

	private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @SuppressWarnings("all")
    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
}
