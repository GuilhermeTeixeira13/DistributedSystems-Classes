package Aula3;

import java.io.Serializable;

public class Aluno implements Serializable {
	int numero;
	String nome;
	String curso;
	int telemovel;
	String email;
	
	public Aluno(int numero, String nome, String curso, int telemovel, String email) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.curso = curso;
		this.telemovel = telemovel;
		this.email = email;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getTelemovel() {
		return telemovel;
	}
	public void setTelemovel(int telemovel) {
		this.telemovel = telemovel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "Aluno [numero=" + numero + ", nome=" + nome + ", curso=" + curso + ", telemovel=" + telemovel
				+ ", email=" + email + "]";
	}
}
