package aplication;

import java.time.LocalDate;

import model.entities.Dependente;
import model.entities.Funcionario;
import model.entities.Pessoa;
import model.entities.enums.ParentescoEnum;

public class Program {

	public static void main(String[] args) {
		Funcionario f1 = new Funcionario("Maria", "01234567890", LocalDate.of(2000, 02, 28), 8500.00);
		Funcionario f2 = new Funcionario("Sergio", "012565667450", LocalDate.of(2000, 06, 28), 3500.0);
		//Funcionario f3 = new Funcionario("Marlon", "012565667450", LocalDate.of(2000,06, 28), 3500.0);

		f1.addDependente("João", "00011122234", LocalDate.of(2018, 03, 01), ParentescoEnum.SOBRINHO);
		f1.addDependente("Joana", "09876543201", LocalDate.of(2017, 10, 01), ParentescoEnum.FILHO);
		//f1.addDependente("Joana", "09876543201", LocalDate.of(2017, 10,01),ParentescoEnum.FILHO);

		f2.addDependente("Bruno", "00011122235", LocalDate.of(2019, 03, 01), ParentescoEnum.FILHO);
		//f2.addDependente("Ana", "00011122236", LocalDate.of(2000, 03, 01),ParentescoEnum.FILHO);

		// Dependente.mostrarListaAllDependentes();

		// Funcionario.mostrarListaAllFuncionarios();

		// f1.mostrarListaDependentes();
		// f2.mostrarListaDependentes();

		System.out.println(f1.getNome() 
						+ " - " 
						+ String.format("%.2f", f1.calcularINSS()) 
						+ " - " 
						+ String.format("%.2f", f1.calcularIR()) + " - " 
						+ String.format("%.2f", f1.calcularSalarioLiquido()));
		
		
		System.out.println(f2.getNome() 
						+ " - " 
						+ String.format("%.2f", f2.calcularINSS())
						+ " - " 
						+ String.format("%.2f", f2.calcularIR()) 
						+ " - " 
						+ String.format("%.2f", f2.calcularSalarioLiquido()));
		
		Pessoa.mostrarListaAllPessoas();
		System.out.println();
		Funcionario.mostrarListaAllFuncionarios();
		System.out.println();
		Dependente.mostrarListaAllDependentes();
						
	}

}
