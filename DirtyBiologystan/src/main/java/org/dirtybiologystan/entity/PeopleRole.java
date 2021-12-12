package org.dirtybiologystan.entity;


import org.springframework.security.core.GrantedAuthority;

/**
 * @author Jérémy Goutelle
 */

public enum PeopleRole implements GrantedAuthority  {
    USER,ADMIN,CITOYEN,NEW_CITOYEN;
	
    @Override
    public String getAuthority() {
        return this.name();
    }
}
