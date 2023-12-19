package com.moriartynho.msinscricaoeducacaoinfantil.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;

public interface GradeRepository extends MongoRepository<Grade, String> {

	Optional<Grade> findByGradeName(String crecheGradeName);

	Optional<List<Grade>> findAllByGradeName(String classGrade);


	boolean existsByGradeMinimumDate(LocalDate gradeMinimumDate);

	boolean existsByGradeMaximumDate(LocalDate gradeMaximumDate);

}
