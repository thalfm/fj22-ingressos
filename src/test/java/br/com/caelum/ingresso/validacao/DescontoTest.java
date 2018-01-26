package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {
	private Filme filme;
	private Sala sala;
	private Sessao sessao;
	private Ingresso ingressoSemDesconto;
	private Ingresso ingressoParaBancos;
	private Ingresso ingressoParaEstudante;
	
	@Before
	public void preparaSessoes(){
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		this.ingressoSemDesconto = new Ingresso(sessao, new SemDesconto());
		this.ingressoParaBancos = new Ingresso(sessao, new DescontoParaBancos());
		this.ingressoParaEstudante = new Ingresso(sessao, new DescontoParaEstudante());
	}
	
	@Test
	public  void naoDeveConcederDescontoParaIngressoNormal(){
		BigDecimal precoEsperado = new BigDecimal("32.50");
		Assert.assertEquals(precoEsperado, ingressoSemDesconto.getPreco());
	}
	
	@Test
	public  void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBanco(){
		BigDecimal precoEsperado = new BigDecimal("22.75");
		Assert.assertEquals(precoEsperado, ingressoParaBancos.getPreco());
	}
	
	@Test
	public  void deveConcederDescontoDe50PorcentoParaIngressosDeClientesDeEstudante(){
		BigDecimal precoEsperado = new BigDecimal("16.25");
		Assert.assertEquals(precoEsperado, ingressoParaEstudante.getPreco());
	}
}
