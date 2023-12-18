package com.moriartynho.msinscricaoeducacaoinfantil.service.validation.insert_class;

import java.util.List;

import org.springframework.stereotype.Component;

import com.moriartynho.msinscricaoeducacaoinfantil.exception.RegisterValidationException;
import com.moriartynho.msinscricaoeducacaoinfantil.model.Grade;
import com.moriartynho.msinscricaoeducacaoinfantil.model.School;

@Component
public class ValidateNumberOfPhysicalRooms implements InsertClassValidation {

	@Override
	public void validate(School schoolToAddClass, List<Grade> gradeOfTheNewClass) throws RegisterValidationException {
		if (schoolToAddClass.getSchoolClasses().size() + 1 > schoolToAddClass.getPhysicalRoomsAvailable()) {
			throw new RegisterValidationException("Essa escola não possui salas físicas disponíveis");
		}

	}

}
