package com.geoschmitt.Devbackend_MaterialEscritorio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.geoschmitt.Devbackend_MaterialEscritorio.entity.Request;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long>{
	
	
	Iterable<Request> findAllByStatusIgnoreCaseContaining(String status);
	
	Iterable<Request> findAllByUserRequesterIgnoreCaseContaining(String userRequester);
	
	Iterable<Request> findAllByPriorityIgnoreCaseContaining(String priority);
	
	Iterable<Request> findAllByPriorityAndStatusIgnoreCaseContainingAndUserRequesterIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(String priority, String status, String userRequester, String description);

}
