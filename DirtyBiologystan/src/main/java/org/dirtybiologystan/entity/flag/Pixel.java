package org.dirtybiologystan.entity.flag;

public class Pixel {

	private Integer colone;
	private Integer ligne;
	private String couleur;
	private Boolean attribuer;
	
	public Pixel(String colone, String ligne) {
		this.colone = Integer.parseInt(colone);
		this.ligne = Integer.parseInt(ligne);
	}
	
	public Pixel(Integer ligne, Integer colone) {
		this.colone = colone;
		this.ligne = ligne;
	}

	public Pixel(int intValue, int intValue2, String couleur2) {
		this(intValue, intValue2);
		this.couleur=couleur2;
	}

	public static Pixel findLatest() {
		// TODO A parti de l'index dans le tableau a 1 ligne. retrouver la position initiale
		return null;
	}

	/**
	 * Méthode qui indique si l'objet courant se trouve avant ou après 
	 * conformément a la désigniation des pixeel sur le drapeau
	 * Si l'object coutant évoque le même pixel quee celui en parametre, il renvoi vrai.
	 * @param pixel
	 * @return true si l'objet courant est avant , faux sinon.
	 */
	public boolean isBeforeOrEqual(Pixel pixel) {
		if (this.colone < pixel.colone) {
			return false;
		}
		if (this.ligne < pixel.ligne) {
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	public Integer getColone() {
		return colone;
	}

	public void setColone(Integer colone) {
		this.colone = colone;
	}

	public Integer getLigne() {
		return ligne;
	}

	public void setLigne(Integer ligne) {
		this.ligne = ligne;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Boolean getAttribuer() {
		return attribuer;
	}

	public void setAttribuer(Boolean attribuer) {
		this.attribuer = attribuer;
	}

	public static Pixel creatPixelTransaprent() {
		return new Pixel(0, 0,"#0000");
	}

}
