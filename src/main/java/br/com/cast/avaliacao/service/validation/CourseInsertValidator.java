package br.com.cast.avaliacao.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cast.avaliacao.controller.exception.FieldMessage;
import br.com.cast.avaliacao.dto.CourseNewDto;
import br.com.cast.avaliacao.repository.CourseRepository;

public class CourseInsertValidator implements ConstraintValidator<CourseInsert, CourseNewDto> {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public void initialize(CourseInsert ann) {
	}

	@Override
	public boolean isValid(CourseNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
//		if (objDto.getBegin()==null) {
//			list.add(new FieldMessage("begin", "Campo de Preenchimento obrigatório"));
//		}
//		
//		if (objDto.getEnd()==null) {
//			list.add(new FieldMessage("end", "Campo de Preenchimento obrigatório"));
//		}
		
		if (!courseRepository.bookedWithBeginDate(objDto.getBegin(), objDto.getCategoryId()).isEmpty()) {
			list.add(new FieldMessage("begin", "Data já ocupada por algum curso"));
		}

		if (!courseRepository.bookedWithEndDate(objDto.getEnd(), objDto.getCategoryId()).isEmpty()) {
			list.add(new FieldMessage("end", "Data já ocupada por algum curso"));
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
