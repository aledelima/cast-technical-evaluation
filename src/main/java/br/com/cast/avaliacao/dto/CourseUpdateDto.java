package br.com.cast.avaliacao.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.cast.avaliacao.service.validation.CourseUpdate;

@CourseUpdate
public class CourseUpdateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String description;
	@NotNull(message="Preenchimento Obrigatório") @FutureOrPresent(message="Data com valor expirado")
	private LocalDate begin;
	@NotNull(message="Preenchimento Obrigatório") @FutureOrPresent(message="Data com valor expirado")
	private LocalDate end;
	private Integer studentsQtd;
	@NotNull
	private Integer categoryId;

	public CourseUpdateDto() {
	}
	
	public CourseUpdateDto(Integer id, String description, LocalDate begin, LocalDate end, Integer studentsQtd, Integer categoryId) {
		this.id = id;
		this.description = description;
		this.begin = begin;
		this.end = end;
		this.studentsQtd = studentsQtd;
		this.categoryId = categoryId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBegin() {
		return begin;
	}

	public void setBegin(LocalDate begin) {
		this.begin = begin;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Integer getStudentsQtd() {
		return studentsQtd;
	}

	public void setStudentsQtd(Integer studentsQtd) {
		this.studentsQtd = studentsQtd;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
