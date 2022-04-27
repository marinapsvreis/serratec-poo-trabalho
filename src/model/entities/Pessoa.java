package model.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;
	protected static Set<Pessoa> listaAllPessoas = new HashSet();

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

	public static Set<Pessoa> getListaAllPessoas() {
		return listaAllPessoas;
	}

	public static void mostrarListaAllPessoas() {
		if (listaAllPessoas.size() == 0) {
			System.out.println();
			System.out.println("O sistema não tem funcionários e nem dependentes cadastrados");
		} else {
			System.out.println();
			System.out.println("A lista de pessoas cadastradas no sistema é: ");
			for (Pessoa p : listaAllPessoas) {
				System.out.println(p);
			}
		}
	}	

	@Override
	public String toString() {
		return nome + " - " + cpf + " - " + dataNascimento;
	}

	public void addPessoaLista(Pessoa p) {
		listaAllPessoas.add(p);
	}

	public void removePessoaLista(Pessoa p) {
		listaAllPessoas.remove(p);
	}

}
