// 
// Decompiled by Procyon v0.5.36
// 

package com.neeyamo.approvalflow.hibernateutility;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiRoutingDataSource extends AbstractRoutingDataSource
{
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getCurrentDb();
    }
}
