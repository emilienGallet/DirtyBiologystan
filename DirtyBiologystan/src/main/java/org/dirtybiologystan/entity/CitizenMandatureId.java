package org.dirtybiologystan.entity;

import java.io.Serializable;

public class CitizenMandatureId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3174148254630225393L;
	private Administration mandater;
	private Citizen citoyen;
	
	public CitizenMandatureId() {
		super();
	}

	public CitizenMandatureId(Administration mandater,Citizen citoyen) {
		super();
		this.citoyen = citoyen;
		this.mandater = mandater;
	}
	
	public Citizen getCitoyen() {
		return citoyen;
	}
	public void setCitoyen(Citizen citoyen) {
		this.citoyen = citoyen;
	}
	public Administration getMandater() {
		return mandater;
	}
	public void setMandater(Administration mandater) {
		this.mandater = mandater;
	}
	
}
