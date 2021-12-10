package org.dirtybiologystan.entity;

import java.util.NoSuchElementException;

import javax.inject.Inject;

import org.dirtybiologystan.repository.CitizenRepository;
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
    CitizenRepository citizenList;

    public final PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Citizen citoyen = citizenList.findById(username).get();
        if (citoyen == null)
            throw new UsernameNotFoundException(username);

        return new User(citoyen.getId(), citoyen.getPassword(), citoyen.getRoles());
    }

    public void save(Citizen citoyen) {
    	String s = citoyen.getPassword();
        citoyen.setPassword(bCryptPasswordEncoder.encode(citoyen.getPassword()));
        bCryptPasswordEncoder.matches(s, citoyen.getPassword());//return false...
        citoyen.getRoles().add(CitizenRole.USER);
        citizenList.save(citoyen);
    }

    public Citizen findById(String username) throws NoSuchElementException{
        return citizenList.findById(username).get();
    }

}
