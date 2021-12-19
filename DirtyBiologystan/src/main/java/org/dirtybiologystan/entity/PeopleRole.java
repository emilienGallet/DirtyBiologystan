package org.dirtybiologystan.entity;


import org.springframework.security.core.GrantedAuthority;

/**
 * @author Jérémy Goutelle
 */

public enum PeopleRole implements GrantedAuthority  {
    CITOYEN,
    NEW_CITOYEN/*,
    USER,
    WEBMASTER,
    MODERATEUR,
    ADMIN*/;
	
    @Override
    public String getAuthority() {
        return this.name();
    }
}
