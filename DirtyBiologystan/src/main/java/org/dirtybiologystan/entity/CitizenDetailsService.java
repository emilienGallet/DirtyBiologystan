package org.dirtybiologystan.entity;

import java.util.NoSuchElementException;

import javax.inject.Inject;

import org.dirtybiologystan.factory.CitizenFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @author Émilien Gallet
 */

@Component
public class CitizenDetailsService implements UserDetailsService {

    @Inject
    CitizenFactory citizenList;

    public final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Citizen people = citizenList.findById(username).get();
        if (people == null)
            throw new UsernameNotFoundException(username);

        return new User(people.getId(), people.getPassword(), people.getRoles());
    }

    public void save(Citizen people) {
        people.setPassword(bCryptPasswordEncoder.encode(people.getPassword()));
        people.getRoles().add(CitizenRole.USER);
        citizenList.save(people);
    }

    public Citizen findById(String username) throws NoSuchElementException{
        return citizenList.findById(username).get();
    }

}
