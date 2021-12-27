package org.dirtybiologystan.entity;

import javax.inject.Inject;

import org.dirtybiologystan.entity.flag.Flag;
import org.dirtybiologystan.entity.flag.Pixel;
import org.dirtybiologystan.factory.PeopleFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author Jérémy Goutelle for IConvoit Project
 * Edited by Émilien Gallet for the dirtybiologistan.
 */

@Component
public class PeopleDetailsService implements UserDetailsService {

    @Inject
    PeopleFactory peopleList;

    public final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        People people = peopleList.findByUsername(username);
        if (people == null)
            throw new UsernameNotFoundException(username);

        return new User(people.getUsername(), people.getPassword(), people.getRoles());
    }

    public void save(People people) {
        people.setPassword(bCryptPasswordEncoder.encode(people.getPassword()));
        peopleList.save(people);
    }

    public People findByUsername(String username) {
        return peopleList.findByUsername(username);
    }
    
    /**
     * Procédure de vérification d'une personne.
     * Notament on vérifie si son pixel n'est pas claim par quelqu'un
     * @param p
     * @return vrai si l'identité est confirmé, faux sinon
     * 
     * L'identité est confirmé si le pixel réclamé n'est pas réquisitionner.
     */
    public boolean checkID(People p,Flag drapeau) {
    	Pixel pix = drapeau.getPixel(p.getLigne(),p.getColone());
    	if (pix==null) {
			return false;
		}
    	return !pix.getAttribuer();
    }

}
