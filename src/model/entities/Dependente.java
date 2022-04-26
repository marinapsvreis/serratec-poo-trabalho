package model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import model.entities.enums.ParentescoEnum;
import model.entities.exceptions.DependenteException;

public class Dependente extends Pessoa {
	private static Set<Dependente> listaAllDependentes = new HashSet<>();
	private ParentescoEnum parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, ParentescoEnum parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		if (eMaiorDeIdade()) {
			throw new DependenteException("Dependente é maior de idade");
		}

	}

	@Override
	public String toString() {
		return super.toString() + " - " + parentesco;
	}

	public static Set<Dependente> getListaAllDependentes() {
		return listaAllDependentes;
	}

	public ParentescoEnum getParentesco() {
		return parentesco;
	}

	public boolean eMaiorDeIdade() {
		LocalDate dataHoje = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataHoje);
		Integer idade = periodo.getYears();
		if (idade >= 18) {
			return true;
		} else {
			return false;
		}

	}

}
