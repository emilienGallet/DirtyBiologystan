package org.dirtybiologistan.factory;

import java.util.Optional;

import org.dirtybiologistan.entity.Citizen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CitizenFactory extends CrudRepository<Citizen, String>{
	
	Optional<Citizen> findById(String username);
	
}
