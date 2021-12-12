package org.dirtybiologystan.factory;

import org.dirtybiologystan.entity.People;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleFactory extends CrudRepository<People, Long>{

	//List<Slot> findAllSlotReserved();

	People findByUsername(String username);
	
	@Transactional(rollbackFor = Exception.class)
	@Modifying
	@Query(nativeQuery = true, value = "insert into PEOPLE_WAYS  values (?1, ?2) " )
	void linkPathPeople(Long idPeople, Long idPath);

	
}
