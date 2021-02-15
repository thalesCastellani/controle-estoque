package br.com.thales.estoque.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.thales.estoque.model.Produto;
import br.com.thales.estoque.model.Unidade;
import lombok.Getter;

@Getter
public class ProdutoDto {
	
	private Long id;
	private String descricao;
	private Unidade unidade;
	private BigDecimal valor;
	private Double quantidade;
	private String ncm;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.unidade = produto.getUnidade();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.ncm = produto.getNcm();
	}

	// usando o Java 8 para converter uma lista de Produto para uma lista de ProdutoDto
	public static List<ProdutoDto> converter(List<Produto> produtos) {
		
		return produtos.stream()
				.map(ProdutoDto::new)
				.collect(Collectors.toList());
	}

}
