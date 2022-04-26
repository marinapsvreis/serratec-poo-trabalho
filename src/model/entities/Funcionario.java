package model.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import model.entities.enums.ParentescoEnum;
import model.entities.interfaces.FuncionarioInterface;

public class Funcionario extends Pessoa implements FuncionarioInterface {

	Double salarioBruto;
	Set<Dependente> listaDependentes = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;

	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Set<Dependente> getListaDependentes() {
		return listaDependentes;
	}

	public void addDependente(String nome, String cpf, LocalDate dataNascimento, ParentescoEnum parentesco) {
		Dependente d = new Dependente(nome, cpf, dataNascimento, parentesco);
		try {
			if (Dependente.getListaAllDependentes().size() == 0) {
				listaDependentes.add(d);
				Dependente.getListaAllDependentes().add(d);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public Double calcularINSS() {
		return null;
	}

	@Override
	public Double calcularIR() {
		return null;
	}

	@Override
	public Double calcularSalarioLiquido() {
		return null;
	}

}
