package com.moriartynho.msinscricaoeducacaoinfantil.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public void generateRanking() throws InternalErrorException, IOException, InterruptedException {
		List<Student> studentsToClassify = studentRepository.findAll();
		List<School> schools = schoolRepository.findAll();
		List<SchoolAndStudents> schoolAndStudents = validateDistanceAndVacancies(studentsToClassify, schools);

		schoolAndStudents.forEach(schoolAndStudent -> updateSchoolAndStudentRanking(schoolAndStudent));
	}

	private void updateSchoolAndStudentRanking(SchoolAndStudents schoolAndStudent) {
		School school = schoolAndStudent.getSchool();
		Student student = schoolAndStudent.getStudent();

		if (!school.getSchoolStudentRanking().contains(student)) {
			school.getSchoolStudentRanking().add(student);
			schoolRepository.save(school);
			studentRepository.save(student);
		}
	}

	private List<SchoolAndStudents> validateDistanceAndVacancies(List<Student> students, List<School> schools)
			throws InternalErrorException, IOException, InterruptedException {
		List<SchoolAndStudents> schoolAndStudentsToAdd = new ArrayList<>();

		for (Student student : students) {
			List<StudentDistanceToSchool> studentDistancesToSchool = calculateDistancesToSchools(student, schools);
			Optional<StudentDistanceToSchool> selectedStudentDistance = selectNearestSchool(studentDistancesToSchool);

			selectedStudentDistance.ifPresent(distanceToSchool -> {
				School selectedSchool = distanceToSchool.getSchool();
				Student selectedStudent = distanceToSchool.getStudent();
				if (!schoolAndStudentsToAdd.contains(selectedStudent)) {
					schoolAndStudentsToAdd.add(new SchoolAndStudents(selectedSchool, selectedStudent));
				}
			});
		}

		return schoolAndStudentsToAdd.stream().distinct().toList();
	}

	private List<StudentDistanceToSchool> calculateDistancesToSchools(Student student, List<School> schools)
			throws InternalErrorException, IOException, InterruptedException {
		return schools.stream()
				.map(school -> new StudentDistanceToSchool(school, student,
						matrixApiClient.compareAddress(school.getSchoolAddress(), student
								.getStudentsAddress())))
				.filter(distanceToSchool -> containsStudentGradeAndContainsVacancies(distanceToSchool.getSchool(),
						distanceToSchool.getStudent())
						&& verifyIfStudentAreInTheSchool(distanceToSchool.getStudent(), distanceToSchool.getSchool()))
				.sorted(Comparator.comparingInt(StudentDistanceToSchool::getDistance)).collect(Collectors.toList());
	}

	private Optional<StudentDistanceToSchool> selectNearestSchool(List<StudentDistanceToSchool> distancesToSchool) {
		return distancesToSchool.stream().findFirst();
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