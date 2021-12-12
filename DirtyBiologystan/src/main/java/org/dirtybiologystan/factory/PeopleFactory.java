package org.dirtybiologystan.factory;

import org.dirtybiologystan.entity.People;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleFactory extends CrudRepository<People, Long>{
	
	People findByUsername(String username);
	
}
