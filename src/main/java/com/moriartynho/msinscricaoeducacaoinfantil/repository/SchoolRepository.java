package com.moriartynho.msinscricaoeducacaoinfantil.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moriartynho.msinscricaoeducacaoinfantil.model.School;

public interface SchoolRepository extends MongoRepository<School, String> {

	boolean existsBySchoolName(String schoolName);

	Optional<School> findBySchoolName(String schoolName);

}
