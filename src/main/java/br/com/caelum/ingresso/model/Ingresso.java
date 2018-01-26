package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.caelum.ingresso.model.descontos.Desconto;

public class Ingresso {
	private Sessao sessao;
	private BigDecimal preco = new BigDecimal("0.0");

	/**
	 * @deprecated hibernate only
	 */
	public Ingresso() {

	}

	public Ingresso(Sessao sessao, Desconto tipoDesconto) {
		this.sessao = sessao;
		this.preco = tipoDesconto.aplicarDescontoSobre(sessao.getPreco());
	}
	
	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
