package model.entities.interfaces;

public interface FuncionarioInterface {
	public static final double aliquotaINSS1 = 1212.01;
	public static final double aliquotaINSS2 = 2427.36;
	public static final double aliquotaINSS3 = 3641.03;
	public static final double aliquotaINSS4 = 7087.22;

	public static final double deducaoINSS1 = 142.8;
	public static final double deducaoINSS2 = 354.8;
	public static final double deducaoINSS3 = 636.13;
	public static final double deducaoINSS4 = 869.36;

	public static final double percentualAliquotaINSS1 = 0.075;
	public static final double percentualAliquotaINSS2 = 0.09;
	public static final double percentualAliquotaINSS3 = 0.12;
	public static final double percentualAliquotaINSS4 = 0.14;

	public static final double aliquotaIR1 = 1903.98;
	public static final double aliquotaIR2 = 2826.66;
	public static final double aliquotaIR3 = 3751.06;
	public static final double aliquotaIR4 = 4664.68;

	public static final double deducaoIR1 = 0;
	public static final double deducaoIR2 = 18.18;
	public static final double deducaoIR3 = 91;
	public static final double deducaoIR4 = 163.82;

	public static final double percentualAliquotaIR1 = 0.075;
	public static final double percentualAliquotaIR2 = 0.15;
	public static final double percentualAliquotaIR3 = 0.225;
	public static final double percentualAliquotaIR4 = 0.275;

	public static final double valorPorDependente = 189.59;

	Double calcularINSS();

	Double calcularIR();

	Double calcularSalarioLiquido();

}
