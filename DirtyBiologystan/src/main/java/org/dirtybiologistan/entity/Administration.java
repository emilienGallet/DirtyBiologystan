package org.dirtybiologistan.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Administration {

	@Id
	@NotNull
	Long id;
	@Column(nullable = false)
	String name;
	@Column(nullable = false)
	String theme;	
	@Column(nullable = true)
	String urlWebsite;
	@Column(nullable = true)
	String urlDiscord;
	@Column(nullable = true)
	ArrayList<String> urlOtherPlatform;
	
	/**
	 * Les citoyens qui sont en charge de l'administration
	 */
	//@OneToMany(cascade = CascadeType.ALL)
	//List<Citizen> administrator;
}
