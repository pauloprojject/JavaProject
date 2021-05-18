package repository;

import java.util.ArrayList;
import java.util.TreeMap;

import classes.Conta;
import classes.Correntista;
import classes.Lancamento;

public class Repositorio {
	private TreeMap<String, Correntista> correntistas;
	private TreeMap<String, Conta> contas;
	private ArrayList<Lancamento> lancamentos;
	public TreeMap<String, Correntista> getCorrentistas() {
		return correntistas;
	}
	public void setCorrentistas(TreeMap<String, Correntista> correntistas) {
		this.correntistas = correntistas;
	}
	public TreeMap<String, Conta> getContas() {
		return contas;
	}
	public void setContas(TreeMap<String, Conta> contas) {
		this.contas = contas;
	}
	public ArrayList<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(ArrayList<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	public Repositorio(TreeMap<String, Correntista> correntistas, TreeMap<String, Conta> contas,
			ArrayList<Lancamento> lancamentos) {
		super();
		this.correntistas = correntistas;
		this.contas = contas;
		this.lancamentos = lancamentos;
	}
}
