package br.com.cast.avaliacao.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cast.avaliacao.controller.exception.FieldMessage;
import br.com.cast.avaliacao.dto.CourseUpdateDto;
import br.com.cast.avaliacao.model.Course;
import br.com.cast.avaliacao.repository.CourseRepository;
import br.com.cast.avaliacao.service.exception.ObjectNotFoundException;

public class CourseUpdateValidator implements ConstraintValidator<CourseUpdate, CourseUpdateDto> {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public void initialize(CourseUpdate ann) {
	}

	public boolean isValid(CourseUpdateDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Course oldCourse = courseRepository.findById(objDto.getId()).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + objDto.getId() + ", Tipo: " + Course.class.getName()));;
		
		if (!oldCourse.getBegin().equals(objDto.getBegin())) {
			if (!courseRepository.bookedWithBeginDate(objDto.getBegin(), objDto.getId()).isEmpty()) {
				list.add(new FieldMessage("begin", "Data já ocupada por algum curso"));
			}
		}

		if (!oldCourse.getEnd().equals(objDto.getEnd())) {
			if (!courseRepository.bookedWithEndDate(objDto.getEnd(), objDto.getId()).isEmpty()) {
				list.add(new FieldMessage("end", "Data já ocupada por algum curso"));
			}
		}
		
		if (objDto.getBegin()!=null && objDto.getEnd()!=null && objDto.getBegin().isAfter(objDto.getEnd())) {
			list.add(new FieldMessage("begin", "Data Inicial posterior a Data Final"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
