package org.dirtybiologystan.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	@Column(nullable = true)
	private LocalDateTime debutValiditer;//Pour la carte d'identité
	@Column(nullable = true)
	private LocalDateTime finValiditer;//Pour la carte d'identité
	@Column(nullable = true)
	private String lieuIRL;
	@Column(nullable = true)
	private String lieuVR;
	@Column(nullable = false)
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

	public Boolean isDirtybiologistanais() {
		return true;
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

	public LocalDateTime getDebutValiditer() {
		return debutValiditer;
	}

	public void setDebutValiditer(LocalDateTime debutValiditer) {
		this.debutValiditer = debutValiditer;
	}

	public LocalDateTime getFinValiditer() {
		return finValiditer;
	}

	public void setFinValiditer(LocalDateTime finValiditer) {
		this.finValiditer = finValiditer;
	}

	public String getLieuIRL() {
		return lieuIRL;
	}

	public void setLieuIRL(String lieuIRL) {
		this.lieuIRL = lieuIRL;
	}

	public String getLieuVR() {
		return lieuVR;
	}

	public void setLieuVR(String lieuVR) {
		this.lieuVR = lieuVR;
	}

	/**
	 * Vérifie qu'il n'y a pas des roles qui s'oppose.
	 * Par exemple le role citoyen et nouveau citoyen
	 * @return
	 */
	public boolean defineRoleGranted() {
		ArrayList<PeopleRole> spr = new ArrayList<PeopleRole>();
		spr.add(PeopleRole.NEW_CITOYEN);
		spr.add(PeopleRole.CITOYEN);
		if (this.getRoles().containsAll(spr)) {
			return false;
		}
		//TODO les autre possibilités
		
		return true;
	}

}
