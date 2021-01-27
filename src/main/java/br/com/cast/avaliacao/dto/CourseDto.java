package br.com.cast.avaliacao.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.cast.avaliacao.model.Course;

public class CourseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String description;
	private LocalDate begin;
	private LocalDate end;
	private Integer studentsQtd;
	private String categoryDescription;
	
	public CourseDto() {
	}

	public CourseDto(Course course) {
		this.id = course.getId();
		this.description = course.getDescription();
		this.begin = course.getBegin();		
		this.end = course.getEnd();
		this.studentsQtd = course.getStudentsQtd();
		this.categoryDescription = course.getCategory().getDescription();
	}
	
	public CourseDto(Integer id, String description, LocalDate begin, LocalDate end, Integer studentsQtd,
			String categoryDescription) {
		this.id = id;
		this.description = description;
		this.begin = begin;
		this.end = end;
		this.studentsQtd = studentsQtd;
		this.categoryDescription = categoryDescription;
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

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
}
