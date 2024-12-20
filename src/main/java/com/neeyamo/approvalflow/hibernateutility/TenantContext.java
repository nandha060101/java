
package com.neeyamo.approvalflow.hibernateutility;



public class TenantContext {
	private TenantContext() {
		throw new IllegalStateException("Utility class");
	}

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenant) {
		clear();
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {

		return currentTenant.get();
	}

	public static void clear() {

		currentTenant.remove();
	}
	
	static {
		TenantContext.currentTenant = new ThreadLocal<>();
	}
}