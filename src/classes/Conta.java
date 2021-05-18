package classes;

import java.util.ArrayList;

public class Conta {
	private String numeros;
	private double saldo;
	private String chavePiks;
	private String tipoChavePiks;
	private ArrayList<Lancamento> lancamentos;
	private Correntista correntista;
	public String getNumeros() {
		return numeros;
	}
	public void setNumeros(String numeros) {
		this.numeros = numeros;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getChavePiks() {
		return chavePiks;
	}
	public void setChavePiks(String chavePiks) {
		this.chavePiks = chavePiks;
	}
	public String getTipoChavePiks() {
		return tipoChavePiks;
	}
	public void setTipoChavePiks(String tipoChavePiks) {
		this.tipoChavePiks = tipoChavePiks;
	}
	public ArrayList<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(ArrayList<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	public Correntista getCorrentista() {
		return correntista;
	}
	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}
	public Conta(String numeros, double saldo, String chavePiks, String tipoChavePiks,
			ArrayList<Lancamento> lancamentos, Correntista correntista) {
		super();
		this.numeros = numeros;
		this.saldo = saldo;
		this.chavePiks = chavePiks;
		this.tipoChavePiks = tipoChavePiks;
		this.lancamentos = lancamentos;
		this.correntista = correntista;
	}
	
	
}
