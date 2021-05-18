package classes;

import java.util.ArrayList;

public class ContaEspecial extends Conta {
	private double limite;

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public ContaEspecial(String numeros, double saldo, String chavePiks, String tipoChavePiks,
			ArrayList<Lancamento> lancamentos, Correntista correntista, double limite) {
		super(numeros, saldo, chavePiks, tipoChavePiks, lancamentos, correntista);
		this.limite = limite;
	}	
}
