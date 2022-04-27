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

		try {
			if (cpfJaCadastrado(cpf)) {
				throw new FuncionarioException("O CPF informado por " + getNome()
						+ " já foi utilizado para outra pessoa. Portanto, não foi cadastrado!");
			} else {
				listaAllFuncionarios.add(this);
				Pessoa.getListaAllPessoas().add(this);
			}
		} catch (FuncionarioException e) {
			System.out.println("Erro: " + e.getMessage());
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
				Pessoa.getListaAllPessoas().add(d);
				listaDependentes.add(d);
			} else {
				if (eMaiorDeIdade(dataNascimento)) {

					throw new DependenteException("Dependente " + d.getNome() + " referente ao funcionario(a) "
							+ getNome() + " já é maior de idade! Portanto, não foi cadastrado(a)!");
				}
				if (cpfJaCadastrado(cpf)) {

					throw new DependenteException("Dependente " + d.getNome() + " referente ao funcionario(a) "
							+ getNome()
							+ " já tem o CPF já cadastrado para outro dependente! Portanto, não foi cadastrado(a)!");
				}

				Dependente.getListaAllDependentes().add(d);
				Pessoa.getListaAllPessoas().add(d);
				listaDependentes.add(d);
			}
		} catch (DependenteException e) {
			System.out.println("Erro: " + e.getMessage());
		}

	}

	public void removerDependente(Dependente dependente) {
		listaDependentes.remove(dependente);
	}

	public static void mostrarListaAllFuncionarios() {
		if (listaAllFuncionarios.size() == 0) {
			System.out.println();
			System.out.println("O sistema não tem funcionarios cadastrados!");
		} else {
			for (Funcionario funcionario : listaAllFuncionarios) {
				System.out.println(funcionario);
			}
		}
	}

	public void mostrarListaDependentes() {
		if (listaDependentes.size() == 0) {
			System.out.println();
			System.out.println("O funcionario " + getNome() + " não tem dependentes!");
		} else {
			System.out.println();
			System.out.println("A lista de dependentes do funcionário " + getNome() + " é: ");
			for (Dependente dependete : listaDependentes) {
				System.out.println(dependete);
			}
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

	public boolean cpfJaCadastrado(String cpf) {
		for (Pessoa p : Pessoa.getListaAllPessoas()) {
			if (cpf.equals(p.getCpf())) {
				return true;
			}
		}
		return false;
	}

	public Double calcularINSS() {
		if (salarioBruto > aliquotaINSS4) {
			return aliquotaINSS4 * percentualAliquotaINSS4 - deducaoINSS4;
		} else if (salarioBruto >= aliquotaINSS3) {
			return salarioBruto * percentualAliquotaINSS4 - deducaoINSS4;
		} else if (salarioBruto >= aliquotaINSS2) {
			return salarioBruto * percentualAliquotaINSS3 - deducaoINSS3; // aqui
		} else if (salarioBruto >= aliquotaINSS1) {
			return salarioBruto * percentualAliquotaINSS2 - deducaoINSS2;
		} else {
			return salarioBruto * percentualAliquotaINSS1;
		}

	}

	@Override
	public Double calcularIR() {
		Double aliquota = (salarioBruto - (listaDependentes.size() * valorPorDependente) - calcularINSS());
		if (aliquota > aliquotaIR4) {
			return aliquota * percentualAliquotaIR4 - deducaoIR4;
		} else if (aliquota >= aliquotaIR3) {
			return aliquota * percentualAliquotaIR3 - deducaoIR3;
		} else if (aliquota >= aliquotaIR2) {
			return aliquota * percentualAliquotaIR2 - deducaoIR2;
		} else if (aliquota >= aliquotaIR1) {
			return aliquota * percentualAliquotaIR1 - deducaoIR1;
		} else {
			return 0.0;
		}
	}

	@Override
	public Double calcularSalarioLiquido() {
		return salarioBruto - calcularINSS() - calcularIR();
	}

}
