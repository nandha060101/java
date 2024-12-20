// 
// Decompiled by Procyon v0.5.36
// 

package com.neeyamo.approvalflow.hibernateutility;

public class DBContextHolder
{
    private static final ThreadLocal<DBTypeEnum> contextHolder;
    
    private DBContextHolder() {
        throw new IllegalStateException("Utility class");
      }
    
    public static void setCurrentDb(final DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }
    
    public static DBTypeEnum getCurrentDb() {
        return contextHolder.get();
    }
    
    public static void clear() {
        contextHolder.remove();
    }
    
    static {
        contextHolder = new ThreadLocal<>();
    }
}
