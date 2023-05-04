package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity // This tells Hibernate to make a table out of this class

public class Professor {
	@Id
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Departamento department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Departamento getDepartment() {
		return department;
	}

	public void setDepartment(Departamento department) {
		this.department = department;
	}

    
}