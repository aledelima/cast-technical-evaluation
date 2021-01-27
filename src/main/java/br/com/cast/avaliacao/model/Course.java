package br.com.cast.avaliacao.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate begin;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
	private Integer studentsQtd;
	
	@ManyToOne
	private Category category;

	public Course() {
	}

	public Course(Integer id, String description, LocalDate begin, LocalDate end, Integer studentsQtd,
			Category category) {
		this.id = id;
		this.description = description;
		this.begin = begin;
		this.end = end;
		this.studentsQtd = studentsQtd;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
