package model.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import model.entities.interfaces.FuncionarioInterface;

public class Funcionario extends Pessoa implements FuncionarioInterface {

	Double salarioBruto;
	Set<Dependente> listaDependente = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Set<Dependente> getListaDependente() {
		return listaDependente;
	}

	public void addDependente(Dependente dependente) {
		this.listaDependente = listaDependente;
	}

	@Override
	public Double calcularINSS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calcularIR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calcularSalarioLiquido() {
		// TODO Auto-generated method stub
		return null;
	}

}
