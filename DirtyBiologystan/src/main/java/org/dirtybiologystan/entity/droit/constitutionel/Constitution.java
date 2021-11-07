package org.dirtybiologystan.entity.droit.constitutionel;

import java.util.List;

import javax.persistence.Id;

import org.dirtybiologystan.entity.droit.Recueil;

public class Constitution extends Recueil{

	String name;
	
	List<ArticleConstitutionel> articles;
}
