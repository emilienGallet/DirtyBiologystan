package org.dirtybiologistan;

public class DeployInit {
	
	public final static Boolean isLive= false;
	
	///////////////////////////////////////////////////////
	////		  		  IMPORTANT					   ////
	////											   ////
	////											   ////
	//// Changer les propriété dans le fichier         ////
	//// application.properties quand on switch entre  ////
	//// Débug et Live                                 ////
	///////////////////////////////////////////////////////
	
	//Debug mod 
	/**/
	public static String Default_Path = System.getProperty("user.dir");
	public static String PathDeploy = Default_Path+"/src/main/resources";//+"/ROOT/WEB-INF/classes";
	public static String PathImgDeploy = PathDeploy + "/static";//""
	public static String NomDeDomaine = "https://localhost:8443/";
	/**/
	//Live Mod
	/*
	public static String Default_Path = "";
	public static String PathDeploy = Default_Path+"";
	public static String PathImgDeploy = PathDeploy + "";//""
	public static String NomDeDomaine = "https://www.dirtybiologistan.org/";
	*/
}