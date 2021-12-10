package org.dirtybiologystan.factory;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.dirtybiologystan.entity.Citizen;
import org.dirtybiologystan.entity.flag.Pixel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CitizenFactory{
	public Citizen citoyen = null;
	public String colone;
	public String ligne;
	public Boolean isSansPixel;
	public String name;
	public String password;
	public String urlPersonalWebsite;
	
	public Citizen getCitoyen() {
		return citoyen;
	}
	public void setCitoyen() {
		//Pixel pixel = new Pixel(colone,ligne);
		//pixelExistant(pixel);
		//pixelClaim(pixel);
		this.citoyen = new Citizen(colone, ligne, password,isSansPixel);
	}
	/**
	 * Methode qui indique si le cadre du drapeau n'as pas été dépassé
	 * @return 
	 * @return
	 */
	private boolean pixelExistant(Pixel pixel) {
		Pixel lastPixel = Pixel.findLatest();
		if (lastPixel.isBeforeOrEqual(pixel)) {
			return true;
		}
		return false;
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
	public Boolean getIsSansPixel() {
		return isSansPixel;
	}
	public void setIsSansPixel(Boolean isSansPixel) {
		this.isSansPixel = isSansPixel;
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
	public String getUrlPersonalWebsite() {
		return urlPersonalWebsite;
	}
	public void setUrlPersonalWebsite(String urlPersonalWebsite) {
		this.urlPersonalWebsite = urlPersonalWebsite;
	}
	
}
