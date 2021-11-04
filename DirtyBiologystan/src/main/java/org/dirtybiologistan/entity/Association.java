package org.dirtybiologistan.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Association {
	@Id
	@NotNull
	Long id;
	@Column(nullable = false)
	String name;
	@Column(nullable = true)
	String urlWebsite;
	@Column(nullable = true)
	String urlDiscord;
	/**
	 * Par défaut a faux
	 */
	@Column(nullable = true)
	Boolean isShare = false;
	@Column(nullable = true)
	ArrayList<String> urlOtherPlatform;
	
}
