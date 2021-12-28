package org.dirtybiologystan.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

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

import org.dirtybiologystan.entity.flag.Flag;
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
	private String email;
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String colone;
	@Column(nullable = false)
	private String ligne;
	@Column(nullable = false)
	private String couleur;
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

	public People() {

	}

	public People(String username, String name, String firstName) {
		this.username = username;
		this.name = name;
		this.firstname = firstName;

	}

	public Boolean isElecteur() {
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

	public Pixel getPixel() {
		return pixel;
	}

	public void setPixel(Pixel pixel) {
		this.pixel = pixel;
		this.colone = pixel.getColone().toString();
		this.ligne = pixel.getLigne().toString();
		this.couleur = pixel.getCouleur();
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

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		//TODO vérification d'usage de l'email
		this.email = email;
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

	public boolean estIlCitoyen() {
		if(roles.contains(PeopleRole.CITOYEN)) {
			return true;
		}
		return false;
	}

	public ArrayList<ArrayList<Pixel>> getVoisin(TreeMap<Integer, TreeMap<Integer, Pixel>> drapeau, int i) {
		Pixel p =  this.getPixel(drapeau);
		ArrayList<Pixel> plist = new ArrayList<>();
		// Parcour de ligne puis colone de gauche a droite
		ArrayList<ArrayList<Pixel>> tab= new ArrayList<>(i+1);
		int j = i/2;
		for (int k = p.getLigne()-j; k < p.getLigne()+j+1; k++) {
			TreeMap<Integer, Pixel> ligne = drapeau.get(k);
			if (ligne == null) {
				//rempli tout la ligne avec des pixel vide
				// #0000
				for (int l = 0; l < i+1; l++) {
					plist.add(Pixel.creatPixelTransaprent());
				}
				tab.add(plist);
			}else {
				int col = p.getColone();
				for (int l = col-j; l < col+j+1; l++) {
					Pixel p2 = ligne.get(l);
					if (p2==null) {
						plist.add(Pixel.creatPixelTransaprent());
					}else {
						plist.add(p2);
					}
				}
				tab.add(plist);
			}
			plist = new ArrayList<>();
		}
		return tab;
	}

	public Pixel getPixel(TreeMap<Integer, TreeMap<Integer, Pixel>> drapeau) {
		return drapeau.get(Integer.parseInt(this.ligne)).get(Integer.parseInt(this.colone));
	}

	/**
	 * Modifie le pixel détenu par l'utilisateur à la couleur donnée
	 * @param drapeau
	 * @param couleur2
	 */
	public void setPixel(Flag f, String couleur2) {
		this.pixel=f.getPixel(ligne, colone);
		this.pixel.setCouleur(couleur2);
	}

}
