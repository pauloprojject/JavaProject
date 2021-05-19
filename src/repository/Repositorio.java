package repository;

import java.util.ArrayList;
import java.util.TreeMap;

import classes.Conta;
import classes.Correntista;
import classes.Lancamento;

public class Repositorio {
	private TreeMap<String, Correntista> correntistas = new TreeMap<>();
	private TreeMap<String, Conta> contas = new TreeMap<>();
	private ArrayList<Lancamento> lancamentos = new ArrayList<>();
	
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
	
	public void adicionar(Correntista correntista) {
		getCorrentistas().put(correntista.getCpf(), correntista);
	}
	public void remover(Correntista correntista) {
		getCorrentistas().remove(correntista.getCpf());			
	}
	public Correntista localizarCorrntista(String cpf) {
		return getCorrentistas().get(cpf);
	}
	
	public void adicionar(Conta conta) {
		getContas().put(conta.getChavePiks(), conta);
	}
	public void remover(Conta conta) {
			getCorrentistas().remove(conta.getChavePiks());			
	}
	public Correntista localizarConta(String chavePiks) {
		return getCorrentistas().get(chavePiks);
	}
	
	public void adicionar(Lancamento lancamento) {
		getLancamentos().add(lancamento);
	}
	public void removerLancamento(Lancamento lancamento) {
		getLancamentos().remove(lancamento);			
	}
}
