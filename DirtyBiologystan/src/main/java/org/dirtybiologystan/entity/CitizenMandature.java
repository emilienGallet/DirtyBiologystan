package org.dirtybiologystan.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.dirtybiologystan.entity.administration.Administration;

import com.sun.istack.NotNull;


@Entity
@IdClass(value = CitizenMandatureId.class)
public class CitizenMandature {
	
	@Id
	@ManyToOne(targetEntity = Administration.class,cascade = CascadeType.ALL)
	Administration mandater;
	@Id
	@ManyToOne(targetEntity = Citizen.class,cascade = CascadeType.ALL)
	Citizen citoyen;
	
	/**
	 * Début de mandat
	 */
	@Transient
	LocalDateTime startOfMandature;
	/**
	 * Fin de mandat
	 */
	@Transient
	LocalDateTime endOfMandature;
}
