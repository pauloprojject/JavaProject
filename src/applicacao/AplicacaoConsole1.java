package applicacao;
import java.time.LocalDate;
import java.util.ArrayList;

import facade.Fachada;
import classes.Conta;
import classes.ContaEspecial;
import classes.Correntista;
import classes.Lancamento;

public class AplicacaoConsole1 {

	public AplicacaoConsole1() {
		try {
			Conta conta;
			ContaEspecial contaesp;

			conta = Fachada.criarConta("1", 			"111.111.001", "9999001", "joao@ifpb", "joao");
			conta = Fachada.criarConta("2", 			"111.111.002", "9999002", "maria@ifpb", "maria");
			contaesp = Fachada.criarConta("3", 100.0,  	"111.111.003", "9999003", "ana@ifpb", "ana");
			contaesp = Fachada.criarConta("4", 100.0, 	"111.111.004", "9999004", "paulo@ifpb", "paulo");

			Fachada.creditar("111.111.001", 500);
			Fachada.creditar("111.111.002", 500);
			Fachada.creditar("111.111.003", 500);
			Fachada.creditar("111.111.004", 500);

			Fachada.criarChave("111.111.001","cpf");
			Fachada.criarChave("111.111.002","telefone");
			Fachada.criarChave("111.111.003","email");
			Fachada.criarChave("111.111.004","aleatorio");

			System.out.println("\n---------listagem geral de contas");
			for(Conta c : Fachada.listarContas()) 
				System.out.println(c);
			System.out.println("\n---------listagem geral de correntistas");
			for(Correntista cor : Fachada.listarCorrentistas()) 
				System.out.println(cor);
			System.out.println("\n---------listagem geral de lan�amentos");
			for(Lancamento lan : Fachada.listarLancamentos()) 
				System.out.println(lan);

			String chavealeatorio = Fachada.obterConta("111.111.004").getChavePiks();
			
			Fachada.transferir("111.111.001", "9999002",  100);	//joao para maria
			Fachada.transferir("111.111.001", "ana@ifpb", 100);	//joao para ana
			Fachada.transferir("111.111.002", "ana@ifpb", 500);	//maria para ana
			Fachada.transferir("111.111.003", "111.111.001", 600);	//ana para joao
			Fachada.transferir("111.111.003", chavealeatorio, 100);	//ana para paulo

			Conta contatop = Fachada.obterContaTop();
			System.out.println("\nconta top="+contatop);
			
			
			Fachada.apagarConta("111.111.001");		

			Fachada.criarChave("111.111.002","aleatorio");


			System.out.println("\n---------listagem geral de contas");
			for(Conta c : Fachada.listarContas()) 
				System.out.println(c);
			System.out.println("\n---------listagem geral de correntistas");
			for(Correntista cor : Fachada.listarCorrentistas()) 
				System.out.println(cor);
			System.out.println("\n---------listagem geral de lan�amentos");
			for(Lancamento lan : Fachada.listarLancamentos()) 
				System.out.println(lan);

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		

		//****************
//		testarExcecoes();
		//****************

	}


	//public static void testarExcecoes() {
//		System.out.println("\n-------TESTE EXCE��ES LAN�ADAS--------");
//		try {
//			Fachada.criarConta("2", "111.111.002", "9999002", "maria@ifpb", "maria");
//			System.out.println("*************1Nao lan�ou exce��o para: criar conta existente "); 
//		}catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}
//
//		try {
//			Fachada.transferir("111.111.002", "111.111.002", 500);	
//			System.out.println("*************2Nao lan�ou exce��o para: chave inexistente"); 
//		}catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}
//
//		try {
//			Fachada.transferir("111.111.002", "9999002", 500);	
//			System.out.println("*************3Nao lan�ou exce��o: conta destino igual a conta origem"); 
//		}catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}
//
//		try {
//			Fachada.transferir("111.111.004", "ana@ifpb", 2000);	
//			System.out.println("*************4Nao lan�ou exce��o: sem saldo"); 
//		}catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}
//
//		try {
//			Fachada.apagarConta("111.111.004");	
//			System.out.println("*************5Nao lan�ou exce��o: saldo deve ser zero"); 
//		}catch (Exception e) {System.out.println("5ok--->"+e.getMessage());}
//
//	}


	public static void main (String[] args) {
		AplicacaoConsole1 aplicacaoConsole1 = new AplicacaoConsole1();
	}
}
