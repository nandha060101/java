
package com.neeyamo.approvalflow.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.neeyamo.approvalflow.dto.SessionDTO;



public class AppUser extends User {

	private static final long serialVersionUID = 1L;
	private SessionDTO sessionDTOFromWidget = null;

	public AppUser(String username, String password, Collection<GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	

	public AppUser(String userName, String clientName, List<GrantedAuthority> grantedRoles,
			SessionDTO sessionDTOFromWidget) {
		super(userName, clientName, grantedRoles);
		this.sessionDTOFromWidget = sessionDTOFromWidget;
	}


	public SessionDTO getSessionDTOFromWidget() {

		return sessionDTOFromWidget;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((sessionDTOFromWidget == null) ? 0 : sessionDTOFromWidget.hashCode());
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		
		  return !((sessionDTOFromWidget == null && other.sessionDTOFromWidget != null)

	                || (sessionDTOFromWidget != null && !sessionDTOFromWidget.equals(other.sessionDTOFromWidget)));
	}

}
