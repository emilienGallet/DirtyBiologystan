package org.dirtybiologystan.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import org.dirtybiologystan.entity.flag.Pixel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Entity
@Component
/**
 * 
 * @author Émilien Managed Citizen entity
 */
public class Citizen{

	@Id
	@NotNull
	private String id;// id for app
	@Column(nullable = false)
	private String colone;
	@Column(nullable = false)
	private String ligne;
	@Column(nullable = true)
	private String name;
	@Transient
	private final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String urlPersonalWebsite;
	@Transient
	private Pixel pixel;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Citizen> familiy;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<CitizenRole> roles = new HashSet<>();
	
	/**
	 * C'est a chier de faire des bean pour des entité ...
	 * Mais pas le choix...
	 */
	public Citizen() {
		super();
	}

	/**
	 * 
	 * @param colone du drapeau lui appartenant
	 * @param ligne du drapeau lui appartenant
	 * @param isSansPixel 
	 */
	public Citizen(String colone,String ligne,String password, Boolean isSansPixel) {
		this.id = bCryptPasswordEncoder.encode(colone+":"+ligne);
	}

	public String getId() {
		return id;
	}

	public void setId(String pixel) {
		pixel.replace("[", "");
		pixel.replace("]", "");
		pixel.replace(")", "");
		pixel.replace("(", "");
		String[] columligne= pixel.split(":");
		if (columligne.length!=2) {
			columligne= pixel.split(",");
		}
		this.id = bCryptPasswordEncoder.encode(columligne[0]+":"+columligne[1]);
	}

	public String getColone() {
		return colone;
	}

	public void setColone(String colone) {
		this.colone = colone;
	}

	public String getLigne() {
		return ligne;
	}

	public void setLigne(String ligne) {
		this.ligne = ligne;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = bCryptPasswordEncoder.encode(password);;
	}

	public Set<CitizenRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<CitizenRole> roles) {
		this.roles = roles;
	}

	public String getUrlPersonalWebsite() {
		return urlPersonalWebsite;
	}

	public void setUrlPersonalWebsite(String urlPersonalWebsite) {
		this.urlPersonalWebsite = urlPersonalWebsite;
	}

	public Pixel getPixel() {
		return pixel;
	}

	public void setPixel(Pixel pixel) {
		this.pixel = pixel;
	}

}
