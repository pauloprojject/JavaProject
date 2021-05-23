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
	public static void apagarConta(String cpf) throws Exception {
		if(repositorio.localizarCorrntista(cpf).getConta().getSaldo() != 0) {
			throw new Exception("Operação inválida: Conta deve ter saldo zerado.");
		}
		for(Lancamento l : obterConta(cpf).getLancamentos()) {
			repositorio.removerLancamento(l);					
		}
		repositorio.remover(obterConta(cpf));
	}
	public static Conta obterConta(String cpf) throws Exception {
		Correntista c = repositorio.localizarCorrntista(cpf);
		if (c == null) {
			throw new Exception("Correntista não existe");
		}
		return c.getConta();
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
	public static Conta criarConta(String numero, String cpf, String telefone, String email, String nome) throws Exception {
		if(repositorio.localizarCorrntista(cpf) != null) {
			throw new Exception("Operação inválida: conta existente");
		}
		Conta c = new Conta();
		c.setNumeros(numero);
		Correntista c2 = new Correntista(cpf, nome, telefone, email);
		c.setCorrentista(c2);
		c2.setConta(c);
//		repositorio.adicionar(c);
		repositorio.adicionar(c2);
		return c;
	}
	public static ContaEspecial criarConta(String numero, double limite, String cpf, String telefone, String email, String nome) {
		Correntista c2 = new Correntista(cpf, nome, telefone, email);
		ContaEspecial c = new ContaEspecial(numero, c2);
		c.setNumeros(numero);
		c.setLimite(limite);
		c2.setConta(c);
//		repositorio.adicionar(c);
		repositorio.adicionar(c2);
		return c;
	}
	public static void creditar(String cpf, double valor) {
		Correntista c = repositorio.localizarCorrntista(cpf);
		Conta c2 = c.getConta();
		c2.creditar(valor);
	}
	public static void criarChave(String cpf, String tipochavePIKS) throws Exception {
		Conta c = obterConta(cpf);
		String key = c.getChavePiks();
		switch(tipochavePIKS) {
			case "cpf":
				if(c.getCorrentista().getCpf().isEmpty()) {
					throw new Exception("Não pode criar chave - cpf vazio");
				}
				c.setChavePiks(c.getCorrentista().getCpf());
				c.setTipoChavePiks(tipochavePIKS);
				repositorio.adicionar(c);
				break;
			case "email":
				if(c.getCorrentista().getEmail().isEmpty()) {
					throw new Exception("Não pode criar chave - Email vazio");
				}

				c.setChavePiks(c.getCorrentista().getEmail());
				c.setTipoChavePiks(tipochavePIKS);
				repositorio.adicionar(c);
				break;
			case "telefone":
				if(c.getCorrentista().getTelefone().isEmpty()) {
					throw new Exception("Não pode criar chave - Telefone vazio");
				}

				c.setChavePiks(c.getCorrentista().getTelefone());
				c.setTipoChavePiks(tipochavePIKS);
				repositorio.adicionar(c);
				break;
			case "aleatorio":
				Random rand = new Random();
				long n = 1000000000 + rand.nextInt(900000000);
				String s = Long.toString(n);
				c.setChavePiks(s.substring(0, 8));
				c.setTipoChavePiks(tipochavePIKS);
				if (!(key == null)) {
					break;
				}
				repositorio.adicionar(c);	
				break;
		}
	}
	public static void transferir(String cpf, String chavePIKS, double quantia) throws Exception {
		Conta origem = obterConta(cpf);
		Conta destino = repositorio.localizarConta(chavePIKS);
		if(destino == null) {
			throw new Exception("Operação inválida: Conta destino inexistente");
		}
		if(origem.equals(destino)) {
			throw new Exception("Operação inválida: conta origem é igual conta destino");
		}
		origem.transferir(quantia, destino);
		Lancamento l1 = new Lancamento(-quantia, destino.getNumeros());
		Lancamento l2 = new Lancamento(quantia, origem.getNumeros());
		origem.adicionarLancamento(l1);
		destino.adicionarLancamento(l2);
		repositorio.adicionarLancamento(l1);
		repositorio.adicionarLancamento(l2);
	}
}
