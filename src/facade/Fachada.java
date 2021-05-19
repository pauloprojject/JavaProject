package facade;

import java.util.ArrayList;
import java.util.Map;

import classes.Conta;
import classes.Correntista;
import classes.Lancamento;
import repository.Repositorio;


public class Fachada {
	private static Repositorio repositorio = new Repositorio();
	public static ArrayList<Conta> listarContas(){
		ArrayList<Conta> contas = new ArrayList<>(); 
		for (Conta s: repositorio.getContas().values()) {
		   contas.add(s);
		}
		return contas;
	}
	public static ArrayList<Correntista> listarCorrentistas(){
		ArrayList<Correntista> correntista = new ArrayList<>(); 
		for (Correntista s: repositorio.getCorrentistas().values()) {
			correntista.add(s);
		}
		return correntista;
	}
	public static ArrayList<Lancamento> listarLancamentos(){
		return repositorio.getLancamentos();
	}
	public static void apagarConta(String cpf) {
		for(Lancamento l : obterConta(cpf).getLancamentos()) {
			repositorio.removerLancamento(l);					
		}
		repositorio.remover(obterConta(cpf));
	}
	public static Conta obterConta(String cpf) {
		Conta c = null;
		for (Map.Entry<String, Conta> entry : repositorio.getContas().entrySet()) {
			if (entry.getValue().getCorrentista().getCpf() == cpf) {
				c = entry.getValue();
			}
		}
		return c;
	}
	public static Conta obterContaTop() {
		Conta c = null;
		int top = -1;
		for (Map.Entry<String, Conta> entry : repositorio.getContas().entrySet()) {
			if(entry.getValue().getLancamentos().size() > top) {
				c = entry.getValue();
			}
		}
		return c;
	}
	
}
