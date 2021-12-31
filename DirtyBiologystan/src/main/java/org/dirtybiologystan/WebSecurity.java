package org.dirtybiologystan;

import javax.inject.Inject;

import org.dirtybiologystan.entity.PeopleDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Jérémy Goutelle
 */

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Inject
    PeopleDetailsService peopleDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/","/drapeau","/register","/css/**","/img/**","/js/**","/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and().formLogin().disable();
            http.csrf().disable();
        http.headers().frameOptions().disable();
    }

     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(peopleDetailsService).passwordEncoder(peopleDetailsService.bCryptPasswordEncoder);
        
        /*if (!DeployInit.isLive) {
        	auth.inMemoryAuthentication()
            .passwordEncoder(peopleDetailsService.bCryptPasswordEncoder) 
            .withUser("emilien").password(peopleDetailsService.bCryptPasswordEncoder.encode("toto")).roles("USER", "ADMIN")
            .and().withUser("bob").password("tata").roles("USER");
		}*/
    }
}
