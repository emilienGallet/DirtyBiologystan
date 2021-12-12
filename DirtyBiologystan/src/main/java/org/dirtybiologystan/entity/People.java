package org.dirtybiologystan.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import org.dirtybiologystan.entity.flag.Pixel;
import org.springframework.stereotype.Component;


@Entity
@Component
/**
 * 
 * @author Émilien Managed People entity
 */
public class People {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;// id for app
	private Long idSource;// if exist official sources
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String colone;
	@Column(nullable = false)
	private String ligne;
	@Column(nullable = true)
	private String urlPersonalWebsite;
	@Column(nullable = false)
	private Boolean isSansPixel;
	@Transient
	private Pixel pixel;

	private String password;

	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<PeopleRole> roles = new HashSet<>();

	@ManyToMany
	private List<People> friend = new ArrayList<People>();

	
	public People() {

	}

	public People(String username, String name, String firstName) {
		this.username = username;
		this.name = name;
		this.firstname = firstName;

	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdSource() {
		return idSource;
	}
	public void setIdSource(Long idSource) {
		this.idSource = idSource;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
		this.password = password;
	}
	public Set<PeopleRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<PeopleRole> roles) {
		this.roles = roles;
	}
	public List<People> getFriend() {
		return friend;
	}
	public void setFriend(List<People> friend) {
		this.friend = friend;
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
	public Boolean getIsSansPixel() {
		return isSansPixel;
	}
	public void setIsSansPixel(Boolean isSansPixel) {
		this.isSansPixel = isSansPixel;
	}

	public void defineRoleGranted() {
		// TODO Auto-generated method stub
		
	}

}
