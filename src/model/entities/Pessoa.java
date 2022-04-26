package model.entities;

import java.time.LocalDate;

public abstract class Pessoa {
	String nome;
	String cpf;
	LocalDate dataNascimento;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento; 
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	@Override
	public String toString() {
		return nome + " - " + cpf + " - " + dataNascimento ;
	}

}
