package aplication;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import model.entities.Funcionario;
import model.entities.enums.ParentescoEnum;

public class Program2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Digite o caminho do arquivo a ser lido:");
		String readPath = sc1.nextLine();
		System.out.println("Digite o caminho do arquivo a ser preenchido:");
		String writePath = sc1.nextLine();
		
		sc1.close();

		InputStream ins = new FileInputStream(readPath);
		Scanner sc2 = new Scanner(ins);

		List<Funcionario> funcionarios = new ArrayList();

		String line;
		while (sc2.hasNextLine()) {
			line = sc2.nextLine();
			if (line.isEmpty()) {
				line = sc2.nextLine();
			}
			String[] fields = line.split(";");
			String[] fieldsData = new String[3];
			
			fieldsData[0] = fields[2].substring(0,4);
			fieldsData[1] = fields[2].substring(4,6);
			fieldsData[2] = fields[2].substring(6,8);
			
			if (!fields[3].toUpperCase().equals("FILHO") && !fields[3].toUpperCase().equals("SOBRINHO") && !fields[3].toUpperCase().equals("OUTROS")) {
				Funcionario funcionario = new Funcionario(fields[0], 
														fields[1], 
														LocalDate.of(Integer.parseInt(fieldsData[0]), Integer.parseInt(fieldsData[1]), Integer.parseInt(fieldsData[2])), 
														Double.parseDouble(fields[3]));
				funcionarios.add(funcionario);
			} else {
				Funcionario fUltimo = funcionarios.get(funcionarios.size()-1);
				fUltimo.addDependente(fields[0], 
									fields[1], 
									LocalDate.of(Integer.parseInt(fieldsData[0]), Integer.parseInt(fieldsData[1]), Integer.parseInt(fieldsData[2])), 
									ParentescoEnum.valueOf(fields[3]));		
			}
		}
		
		sc2.close();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(writePath))){
			for (Funcionario resultado : funcionarios){
				bw.write(resultado.getNome() + ";" 
						+ resultado.getCpf() + ";"
						//+ resultado.getListaDependentes().size() + ";" 
						//+ String.format("%.2f", resultado.getListaDependentes().size() * Funcionario.valorPorDependente) + ";" 
						+ String.format("%.2f", resultado.calcularINSS()) + ";" 
						+ String.format("%.2f", resultado.calcularIR()) + ";" 
						+ String.format("%.2f", resultado.calcularSalarioLiquido()));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
