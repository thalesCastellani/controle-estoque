package br.com.thales.estoque.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.thales.estoque.model.Produto;
import br.com.thales.estoque.model.Unidade;
import br.com.thales.estoque.repository.ProdutoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoForm {

	@Length(min = 5)	
	@NotNull	
	@NotBlank // NotBlank.produtoForm.descricao
	private String descricao;

	private Unidade unidade;

	@DecimalMin(value = "0.1")
	private BigDecimal valor;

	@DecimalMin(value = "0.1")
	private Double quantidade;

	@Length(min = 8, max = 10)
	@NotNull
	@NotBlank
	private String ncm;

	public Produto converter() {
		return new Produto(descricao, unidade, valor, quantidade, ncm);
	}

	public Produto atualiza(Long id, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.getOne(id);
		
		produto.setDescricao(this.descricao);
		produto.setUnidade(this.unidade);
		produto.setValor(this.valor);
		produto.setQuantidade(this.quantidade);
		produto.setNcm(this.ncm);
		
		return produto;
	}
}
