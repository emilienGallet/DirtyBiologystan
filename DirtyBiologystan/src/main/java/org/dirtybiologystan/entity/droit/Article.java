package org.dirtybiologystan.entity.droit;

import javax.persistence.Id;

public abstract class Article {

	/*As ID*/
	ArticleIdentifier<? extends Recueil> dansLeReccueil;
	
}
