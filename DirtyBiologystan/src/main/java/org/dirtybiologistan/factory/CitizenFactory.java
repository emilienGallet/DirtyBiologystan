package org.dirtybiologistan.factory;

import org.dirtybiologistan.entity.Citizen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CitizenFactory extends CrudRepository<Citizen, Long>{

	//List<Slot> findAllSlotReserved();

	Citizen findById(String username);
	
	@Transactional(rollbackFor = Exception.class)
	@Modifying
	@Query(nativeQuery = true, value = "insert into PEOPLE_WAYS  values (?1, ?2) " )
	void linkPathPeople(Long idPeople, Long idPath);

	
}
