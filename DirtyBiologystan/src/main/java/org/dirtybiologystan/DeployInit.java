package org.dirtybiologystan;

public class DeployInit {
	
	public final static Boolean isLive=true;

	
	///////////////////////////////////////////////////////
	////		  		  IMPORTANT					   ////
	////											   ////
	////											   ////
	//// Changer les propriété dans le fichier         ////
	//// application.properties quand on switch entre  ////
	//// Débug et Live                                 ////
	///////////////////////////////////////////////////////
	
	//Debug mod 
	/*
	public static final String Default_Path = System.getProperty("user.dir");
	public static final String PathDeploy = Default_Path+"/src/main/resources";//+"/ROOT/WEB-INF/classes";
	public static final String PathImgDeploy = PathDeploy + "/static";//""
	public static final String PathResourcesDeploy = PathDeploy;
	public static final String NomDeDomaine = "http://localhost:8080/";
	*/
	//Live Mod
	/**/
	public static final String Default_Path = "";
	public static final String PathDeploy = Default_Path+"";
	public static final String PathImgDeploy = PathDeploy + "";//""
	public static final String PathResourcesDeploy = "/dirtybiologistan";
	public static final String NomDeDomaine = "https://www.dirtybiologistan.org/";//Déja pris a modifier
	/**/
}