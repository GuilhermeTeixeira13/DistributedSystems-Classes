package com.example.demo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class

public class Departamento {
	@Id //depId is the primary key
	private Integer depId;
	private String nome;
	
	@OneToMany(mappedBy = "department")
    private List<Professor> professores;
	
	
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Professor> getProfessores() {
		return professores;
	}
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}	
}
