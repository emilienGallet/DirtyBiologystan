package org.dirtybiologystan.entity.flag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

import org.dirtybiologystan.DeployInit;
import org.springframework.boot.json.GsonJsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class Flag {
	public Stack<Pixel> pixies;
	public TreeMap<Integer,TreeMap<Integer,Pixel>> drapeau;//ligne puis colone;
	static Float ratio = 0.5f; 
	public Flag() {
		pixies = new Stack<>();
		drapeau = new TreeMap<Integer,TreeMap<Integer,Pixel>>();
	}
	
	public void chargerDataFromFouloscopieAndCodati() throws Exception {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//En date du 4 décembre
		String data = new BufferedReader(new FileReader(DeployInit.PathResourcesDeploy+"/templates/flag/flag.html")).readLine();
		String jsonString = gson.toJson(data);
		List<LinkedTreeMap<?, ?>> lofficiel = gson.fromJson(data,List.class);
		//En date du 5 décembre
		data = new BufferedReader(new FileReader(DeployInit.PathResourcesDeploy+"/templates/flag/codati.html")).readLine();
		jsonString = gson.toJson(data);
		List<LinkedTreeMap<?, ?>> lcodati = gson.fromJson(data,List.class);
		//Pour chaque objets de chaque liste on va récupéré l'index au drapeau.
		//Puis on vérifie que les entityId,author sont équivalente strictement
		//Sinon est envoyer comme erreur
		//Puis on vérifie pour les pixel si différent c'est un warning
		boolean continuer = true;
		int i =0, lo_size = lofficiel.size()-1,lc_size=lcodati.size()-1;
		//LinkedTreeMap o = null,oo = null;
		ArrayList<Pixel> indexError = new ArrayList<>();
		ArrayList<Pixel> authorError = new ArrayList<>();
		ArrayList<Pixel> hexColorError = new ArrayList<>();
		ArrayList<Pixel> index = new ArrayList<>();
		
		HashMap<Integer, LinkedTreeMap<?, ?>> map = new HashMap<>();
		for (LinkedTreeMap<?, ?> el : lcodati) {
			Double indexInFlagCodati = (Double) el.get("index");
			map.put(indexInFlagCodati.intValue(), el);
			System.out.println("Rajout à l'index"+ indexInFlagCodati.intValue());
			//System.out.println(map.get(1));
			
		}
		/*for (LinkedTreeMap<?, ?> el : lofficiel) {
			Double indexInFlag = (Double) el.get("indexInFlag");
			map.put(indexInFlag.intValue(), el);
			System.out.println("Rajout à l'index"+ indexInFlag.intValue());
			//System.out.println(map.get(1));
			
		}
		
		for (LinkedTreeMap<?, ?> el : lcodati) {
			Double indexInFlag = (Double) el.get("indexInFlag");
			Double ligne = (Double) el.get("x");
			Double colone = (Double) el.get("y");
			Pixel p = new Pixel(ligne.intValue(),colone.intValue());
			
			if (map.containsKey(indexInFlag.intValue())) {
				LinkedTreeMap<?, ?> oo = map.get(indexInFlag.intValue());
				String author = (String) oo.get("author");
				String hexColor = (String) oo.get("hexColor").toString();
				if (!(author.compareTo((String) el.get("author"))==0)) {
					authorError.add(p);
				}
				else if (!(hexColor.compareTo((String) el.get("hexColor"))==0)) {
					hexColorError.add(p);
					map.put(indexInFlag.intValue(), el);
				}else {
					map.put(indexInFlag.intValue(), el);
				}
				
			}else {
				System.out.println("map non found " + indexInFlag);
				map.put(((Double) el.get("index")).intValue(), el);//Si on prend les données de codati
			}
		}
		*/
		Integer k =0;
		for (int j = 0; j < map.size()-1; j++) {
			//map.entrySet();
			Pixel p;
			try{
				Double ligne = (Double) map.get(k).get("x");
				Double colone = (Double) map.get(k).get("y");
				String couleur = map.get(k).get("hexColor").toString();
				int y = colone.intValue();
				int x = ligne.intValue();
				p = new Pixel(y,x,couleur);
				rajouterPixelFromData(p);
				//index.add(p);
			}catch (Exception e) {
				System.out.println(k);
				
				System.err.println(e.getMessage());
			}
			k++;
		}
		/*pixies.addAll(index);
		
		System.out.println("IndexError : "+indexError.size());
		System.out.println("AuthorError : "+authorError.size());
		System.out.println("hexColorError : "+hexColorError.size());
		System.out.println("index : "+index.size());*/
	}

	
	private void rajouterPixelFromData(Pixel p) {
		pixies.add(p);/*
		int x = p.getLigne()-1;
		if (x > drapeau.size()) {
			ArrayList<Pixel> ligne = new ArrayList<Pixel>();
			ligne.add(p);
			drapeau.add(ligne);
		}else {
			drapeau.get(x).add(p);
		}*/
		try {
			int x = p.getLigne()-1;
			int y =p.getColone()-1;
			drapeau.get(x).put(y, p);
		} catch (Exception e) {
			int x = p.getLigne()-1;
			int y =p.getColone()-1;
			TreeMap<Integer,Pixel> tm = new TreeMap<>();
			tm.put(y, p);
			drapeau.put(x, tm);
		}
	}
}
