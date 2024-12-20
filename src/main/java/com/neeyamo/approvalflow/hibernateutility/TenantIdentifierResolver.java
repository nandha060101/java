
package com.neeyamo.approvalflow.hibernateutility;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	public static final String DEFAULT_TENANT_ID = "approvalflow";
	public static final String PREFIX_TENANT_ID = "approvalflow_";
	@Override
	public String resolveCurrentTenantIdentifier() {

		String tenantId = TenantContext.getCurrentTenant();
		if (tenantId != null) {
			return tenantId;
		}
		return DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {

		return true;
	}
}
