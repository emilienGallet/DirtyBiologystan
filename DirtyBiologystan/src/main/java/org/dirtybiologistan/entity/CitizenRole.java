package org.dirtybiologistan.entity;


import org.springframework.security.core.GrantedAuthority;

/**
 * @author Émilien Gallet
 * A comprendre rôle dans le sens du site et non dans le pays
 */
public enum CitizenRole implements GrantedAuthority  {
    USER,ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
