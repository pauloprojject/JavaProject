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

	public ContaEspecial(String numeros, double saldo, String chavePiks, String tipoChavePiks, Correntista correntista, double limite) {
		super(numeros, chavePiks, tipoChavePiks, correntista);
		this.limite = limite;
	}

	public ContaEspecial(String numeros, double saldo, String chavePiks, String tipoChavePiks,
			Correntista correntista) {
		super(numeros, chavePiks, tipoChavePiks, correntista);
	}
	public ContaEspecial(String numeros, Correntista correntista) {
		super(numeros, correntista);
	}
	@Override
	public void debitar(double quantia) throws Exception {
		if ((getSaldo() - quantia) < limite) {	
			throw new Exception("Operação inválida: Limite estourado.");
		}
		setSaldo(getSaldo()-quantia);
	}
	@Override
	public String toString() {
		return "Conta [numeros=" + getNumeros() + ", saldo=" + getSaldo() + ", chavePiks=" + getChavePiks() + ", tipoChavePiks="
				+ getTipoChavePiks() + ", Limite=" + limite + "]";
	}
}
