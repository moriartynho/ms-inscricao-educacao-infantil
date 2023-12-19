package com.moriartynho.msinscricaoeducacaoinfantil.builder;

import java.time.LocalDate;

import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;

public class GradeEditBuilder {
	private String gradeName;
	private LocalDate gradeMinimumDate;
	private LocalDate gradeMaximumDate;

	public GradeEditBuilder withGradeName(String gradeName) {
		this.gradeName = gradeName;
		return this;
	}

	public GradeEditBuilder withGradeMinimumDate(LocalDate gradeMinimumDate) {
		this.gradeMinimumDate = gradeMinimumDate;
		return this;
	}

	public GradeEditBuilder withGradeMaximumDate(LocalDate gradeMaximumDate) {
		this.gradeMaximumDate = gradeMaximumDate;
		return this;
	}

	public Grade build(Grade existingGrade) {
		String finalGradeName = (!gradeName.isEmpty()) ? gradeName : existingGrade.getGradeName();
		LocalDate finalGradeMinimumDate = (gradeMinimumDate != null) ? gradeMinimumDate
				: existingGrade.getGradeMinimumDate();
		LocalDate finalGradeMaximumDate = (gradeMaximumDate != null) ? gradeMaximumDate
				: existingGrade.getGradeMaximumDate();

		existingGrade.setGradeName(finalGradeName);
		existingGrade.setGradeMinimumDate(finalGradeMinimumDate);
		existingGrade.setGradeMaximumDate(finalGradeMaximumDate);

		return existingGrade;
	}

}
