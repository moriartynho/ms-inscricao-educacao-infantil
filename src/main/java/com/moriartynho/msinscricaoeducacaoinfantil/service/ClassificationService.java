package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.InternalErrorException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;
import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolAndStudents;
import com.moriartynho.msinscricaoeducacaoinfantil.model.SchoolClass;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Student;
import com.moriartynho.msinscricaoeducacaoinfantil.model.StudentDistanceToSchool;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.SchoolRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.repository.StudentRepository;
import com.moriartynho.msinscricaoeducacaoinfantil.service.distance_matrix_api.DistanceMatrixApiClient;

@Service
public class ClassificationService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DistanceMatrixApiClient matrixApiClient;

	public void generateRanking() throws InternalErrorException, IOException, InterruptedException {
		List<Student> studentsToClassify = studentRepository.findAll();
		List<School> schools = schoolRepository.findAll();
		List<SchoolAndStudents> schoolAndStudents = validateDistanceAndVacancies(studentsToClassify, schools);

		for (SchoolAndStudents schoolAndStudent : schoolAndStudents) {
			School school = schoolAndStudent.getSchool();
			Student student = schoolAndStudent.getStudent();

			if (!school.getSchoolStudentRanking().contains(student)) {
				school.getSchoolStudentRanking().add(student);
				schoolRepository.save(school);
				studentRepository.save(student);
			}
		}
	}

	private List<SchoolAndStudents> validateDistanceAndVacancies(List<Student> students, List<School> schools)
			throws InternalErrorException, IOException, InterruptedException {
		List<StudentDistanceToSchool> studentDistancesToSchool = new ArrayList<>();
		List<SchoolAndStudents> schoolAndStudentsToAdd = new ArrayList<>();

		for (Student student : students) {
			for (School school : schools) {
				int distanceBetweenStudentAndSchool = matrixApiClient.compareAddress(school.getSchoolAddress(),
						student.getStudentsAddress());
				StudentDistanceToSchool studentDistanceToSchool = new StudentDistanceToSchool(school, student,
						distanceBetweenStudentAndSchool);

				if (containsStudentGradeAndContainsVacancies(school, student)
						&& verifyIfStudentAreInTheSchool(student, school)) {

					studentDistancesToSchool.add(studentDistanceToSchool);
				}
				studentDistancesToSchool.sort(Comparator.comparingInt(StudentDistanceToSchool::getDistance));
				School selectedSchool = studentDistancesToSchool.get(0).getSchool();
				Student selectedStudent = studentDistancesToSchool.get(0).getStudent();
				if (!schoolAndStudentsToAdd.contains(selectedStudent)) {
					schoolAndStudentsToAdd.add(new SchoolAndStudents(selectedSchool, selectedStudent));
				}

			}
			studentDistancesToSchool.clear();
		}
		return schoolAndStudentsToAdd.stream().distinct().toList();

	}

	private boolean verifyIfStudentAreInTheSchool(Student studentToCompare, School school) {
		return !school.getSchoolStudentRanking().stream()
				.anyMatch(student -> student.getStudentsFullName().equals(studentToCompare.getStudentsFullName()));
	}

	private boolean containsStudentGradeAndContainsVacancies(School school, Student student) {
		int totalVacancies = school.getSchoolClasses().stream()
				.filter(schoolClass -> schoolClass.getClassGrade().get(0).getGradeName()
						.equals(student.getStudentGrade().getGradeName()))
				.mapToInt(SchoolClass::getMaximumVacanciesInTheClass).sum();

		boolean quantityVacaniesByGradeCheck = totalVacancies >= school.getSchoolStudentRanking().size();
		boolean gradeDisponibiltyCheck = school.getSchoolClasses().stream().anyMatch(schoolClass -> schoolClass
				.getClassGrade().get(0).getGradeName().equals(student.getStudentGrade().getGradeName()));

		return quantityVacaniesByGradeCheck && gradeDisponibiltyCheck;
	}

}