package org.dirtybiologistan.entity;


import org.springframework.security.core.GrantedAuthority;

/**
 * @author Émilien Gallet
 */

public enum CitizenRole implements GrantedAuthority  {
    USER,ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
