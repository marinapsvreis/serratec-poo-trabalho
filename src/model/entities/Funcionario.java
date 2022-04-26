package model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import model.entities.enums.ParentescoEnum;
import model.entities.exceptions.DependenteException;
import model.entities.exceptions.FuncionarioException;
import model.entities.interfaces.FuncionarioInterface;

public class Funcionario extends Pessoa implements FuncionarioInterface {

	private Double salarioBruto;
	private Set<Dependente> listaDependentes = new HashSet<>();
	private static Set<Funcionario> listaAllFuncionarios = new HashSet<>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, Double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		if (cpfJaCadastradoFuncionarios(cpf)) {
			throw new FuncionarioException(
					"O CPF informado já foi utilizado para outro funcionario. Portanto, não foi cadastrado!");
		} else {
			listaAllFuncionarios.add(this);
		}

	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public Set<Dependente> getListaDependentes() {
		return listaDependentes;
	}

	public static Set<Funcionario> getListaAllFuncionarios() {
		return listaAllFuncionarios;
	}

	public void addDependente(String nome, String cpf, LocalDate dataNascimento, ParentescoEnum parentesco) {
		Dependente d = new Dependente(nome, cpf, dataNascimento, parentesco);
		try {
			if (Dependente.getListaAllDependentes().size() == 0) {
				Dependente.getListaAllDependentes().add(d);
				listaDependentes.add(d);
			} else {
				if (eMaiorDeIdade(dataNascimento)) {

					throw new DependenteException("Dependente " + d.getNome() + " referente ao funcionario(a) "
							+ getNome() + " já é maior de idade! Portanto, não foi cadastrado(a)!");
				}
				if (cpfJaCadastradoDependentes(cpf)) {

					throw new DependenteException("Dependente " + d.getNome() + " referente ao funcionario(a) "
							+ getNome()
							+ " já tem o CPF já cadastrado para outro dependente! Portanto, não foi cadastrado(a)!");
				}

				Dependente.getListaAllDependentes().add(d);
				listaDependentes.add(d);
			}
		} catch (DependenteException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public boolean eMaiorDeIdade(LocalDate dataNascimento) {
		LocalDate dataHoje = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataHoje);
		Integer idade = periodo.getYears();
		if (idade >= 18) {
			return true;
		} else {
			return false;
		}

	}

	public boolean cpfJaCadastradoDependentes(String cpf) {
		for (Dependente d : Dependente.getListaAllDependentes()) {
			if (cpf.equals(d.getCpf())) {
				return true;
			}
		}
		return false;
	}

	public boolean cpfJaCadastradoFuncionarios(String cpf) {
		for (Funcionario f : Funcionario.getListaAllFuncionarios()) {
			if (cpf.equals(f.getCpf())) {
				return true;
			}
		}
		return false;
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
