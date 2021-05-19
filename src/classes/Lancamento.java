package classes;

import java.time.LocalDateTime;

public class Lancamento {
	private LocalDateTime dataHora;
	private double valor;
	private String numero;
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Lancamento(double valor, String numero) {
		super();
		this.dataHora = LocalDateTime.now();
		this.valor = valor;
		this.numero = numero;
	}
}
