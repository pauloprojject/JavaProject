package facade;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import classes.Conta;
import classes.ContaEspecial;
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
	public static Conta criarConta(String numero, String cpf, String telefone, String email, String nome) {
		Conta c = new Conta();
		c.setNumeros(numero);
		Correntista c2 = new Correntista(cpf, nome, telefone, email, c);
		c.setCorrentista(c2);
		repositorio.adicionar(c);
		repositorio.adicionar(c2);
		return c;
	}
	public static ContaEspecial criarConta(String numero, double limite, String cpf, String telefone, String email, String nome) {
		Correntista c2 = new Correntista(cpf, nome, telefone, email);
		ContaEspecial c = new ContaEspecial(numero, c2);
		c.setNumeros(numero);
		c.setLimite(limite);
		c2.setConta(c);
		repositorio.adicionar(c);
		repositorio.adicionar(c2);
		return c;
	}
	public static void creditar(String cpf, double valor) {
		double soma = 0;
		for (Map.Entry<String, Correntista> entry : repositorio.getCorrentistas().entrySet()) {
			if (entry.getValue().getCpf() == cpf) {
				soma = obterConta(cpf).getSaldo() + valor;
			}
		}
		obterConta(cpf).setSaldo(soma);
	}
	public static void criarChave(String cpf, String tipochavePIKS) {
		Random rand = new Random();
		int n = 100000 + rand.nextInt(900000);
		Conta c = obterConta(cpf);
		switch(tipochavePIKS) {
			case "cpf":
				c.setChavePiks(c.getCorrentista().getCpf());
				c.setTipoChavePiks(tipochavePIKS);
				break;
			case "email":
				c.setChavePiks(c.getCorrentista().getEmail());
				c.setTipoChavePiks(tipochavePIKS);
				break;
			case "telefone":
				c.setChavePiks(c.getCorrentista().getTelefone());
				c.setTipoChavePiks(tipochavePIKS);
				break;
			case "aleatoria":
				c.setChavePiks(Integer.toString(n));
				c.setTipoChavePiks(tipochavePIKS);
				break;
		}
	}
	public static void transferir(String cpf, String chavePIKS, double quantia) {
		Conta c = obterConta(cpf);
		for(Map.Entry<String, Conta> entry : repositorio.getContas().entrySet()) {
			if(entry.getValue().getChavePiks() == chavePIKS) {
				creditar(entry.getValue().getCorrentista().getCpf(), quantia);
				entry.getValue().getLancamentos().add(new Lancamento(quantia, entry.getValue().getNumeros()));
			}
		}
		c.setSaldo(c.getSaldo() - quantia);
		c.getLancamentos().add(new Lancamento(-quantia, c.getNumeros()));
	}
}
