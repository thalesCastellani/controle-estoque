package br.com.thales.estoque.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Unidade unidade;
	private BigDecimal valor;
	private Double quantidade;
	private String ncm;
	private LocalDate data = LocalDate.now();
	
	public Produto(String descricao, Unidade unidade, BigDecimal valor, Double quantidade, String ncm) {

		this.descricao = descricao;
		this.unidade = unidade;
		this.valor = valor;
		this.quantidade = quantidade;
		this.ncm = ncm;
	}
	
	

}
