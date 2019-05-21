package com.geoschmitt.Devbackend_MaterialEscritorio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geoschmitt.Devbackend_MaterialEscritorio.entity.Request;
import com.geoschmitt.Devbackend_MaterialEscritorio.repository.RequestRepository;

@RestController
public class RequestController  {

	@Resource
	private EntityManager emger;
	
	private RequestRepository requestRepository;

	public RequestController(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}
	
	@GetMapping(path = "/requests")
	public ResponseEntity<List<Request>> findAll() {
		return ResponseEntity.ok(StreamSupport.stream(
				requestRepository
				.findAll()
				.spliterator(),false)
				.collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/request/{userRequester}")
	public ResponseEntity<List<Request>> findAllByUserRequester(@PathVariable String userRequester) {
		return ResponseEntity.ok(StreamSupport
				.stream(
						requestRepository
						.findAllByUserRequesterIgnoreCaseContaining(userRequester)
						.spliterator(),false
						).collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/filter")
	public ResponseEntity<List<Request>> findAllByPriorityAndStatusIgnoreCaseContainingAndUserRequesterIgnoreCaseContainingAndDescriptionIgnoreCaseContaining(@RequestParam(required=false) String priority, @RequestParam(required=false) String status, @RequestParam(required=false) String request, @RequestParam(required=false) String description) {
		
		CriteriaBuilder cb = emger.getCriteriaBuilder();
		CriteriaQuery<Request> cq;
		cq = cb.createQuery(Request.class);
		Root<Request> root = cq.from(Request.class);
		ArrayList<Predicate> predicat = new ArrayList<>();
		if (status!=null && !status.isEmpty()) predicat.add(cb.equal(root.get(Request.STATUS), status));
		
		if (request!=null && !request.isEmpty()) {
			Predicate exp = cb.like(root.get(Request.REQUESTER), request);
			if(predicat.size() == 0) predicat.add(exp); 
			else predicat.add(cb.and(exp));
		}
		
		if (description!=null && !description.isEmpty()) {
			Predicate exp = cb.like(root.get(Request.DESCRIPTION), description);
			if (predicat.size()==0) predicat.add(exp); 
			else predicat.add(cb.and(exp));
		}
		
		if (predicat.size()>0) cq.where(predicat.toArray(new Predicate[]{}));
		
		TypedQuery<Request> query = emger.createQuery(cq);
		return ResponseEntity.ok(query.getResultList());
	}
	
	@GetMapping(path = "/request/{status}")
	public ResponseEntity<List<Request>> findAllByStatus(@PathVariable String status) {
		return ResponseEntity.ok(StreamSupport
				.stream(
						requestRepository
						.findAllByStatusIgnoreCaseContaining(status)
						.spliterator(),false
						).collect(Collectors.toList()));
	}
	
	@GetMapping(path = "/request/{id}")
	public ResponseEntity<Request> findById(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(requestRepository
				.findById(id)
				.orElseThrow(() -> new Exception("ID NOT FOUND")));
	}
	
	@GetMapping(path = "/request/{priority}")
	public ResponseEntity<List<Request>> findAllByPriority(@PathVariable String priority) {
		return ResponseEntity.ok(StreamSupport
				.stream(
						requestRepository
						.findAllByPriorityIgnoreCaseContaining(priority)
						.spliterator(),false
						).collect(Collectors.toList()));
	}
	
	@PostMapping(path = "/request")
	public ResponseEntity<Request> create(@Valid @RequestBody Request request) {
		return ResponseEntity.ok(
				requestRepository
				.save(request)
				);
	}
	
	@PutMapping(path = "/request")
	public ResponseEntity<Request> att(@Valid @RequestBody Request request) {
		return ResponseEntity.ok(
				requestRepository
				.save(request)
				);
	}
	
}
