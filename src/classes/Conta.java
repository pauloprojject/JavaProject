package classes;

import java.util.ArrayList;

public class Conta {
	private String numeros;
	private double saldo = 0;
	private String chavePiks;
	private String tipoChavePiks;
	private ArrayList<Lancamento> lancamentos = new ArrayList<>();
	private Correntista correntista;
	public String getNumeros() {
		return numeros;
	}
	public void creditar(double valor) {
		this.saldo += valor;
	}
	@Override
	public String toString() {
		return "Conta [numeros=" + numeros + ", saldo=" + saldo + ", chavePiks=" + chavePiks + ", tipoChavePiks="
				+ tipoChavePiks + "]";
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
	public Conta(String numeros, String chavePiks, String tipoChavePiks, Correntista correntista) {
		super();
		this.numeros = numeros;
		this.chavePiks = chavePiks;
		this.tipoChavePiks = tipoChavePiks;
		this.correntista = correntista;
	}
	public Conta(String numeros, Correntista correntista) {
		super();
		this.numeros = numeros;
		this.correntista = correntista;
	}
	public Conta() {
		super();
	}
	public void debitar(double quantia) throws Exception {
		if ((getSaldo() - quantia) < 0) {
			throw new Exception("Opera??o inv?lida: Cr?dito insuficiente.");
		}
		this.saldo -= quantia;
	}
	public void transferir(double quantia, Conta destino) throws Exception {
		this.debitar(quantia);
		destino.creditar(quantia);
	}
	public void adicionarLancamento(Lancamento lancamento) {
		this.lancamentos.add(lancamento);
	}
	
	
}
