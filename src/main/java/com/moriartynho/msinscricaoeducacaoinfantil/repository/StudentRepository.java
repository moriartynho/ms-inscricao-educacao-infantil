package com.moriartynho.msinscricaoeducacaoinfantil.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {

	boolean existsByStudentsCpf(String studentsCpf);

}
