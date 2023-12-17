package com.moriartynho.msinscricaoeducacaoinfantil.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolClass;

public interface SchoolClassRepository extends MongoRepository<SchoolClass, String> {

}
