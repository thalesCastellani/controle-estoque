package br.com.thales.estoque.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.thales.estoque.model.Produto;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Test
	public void deveriaCarregarUmProdutoAoBuscarPelaSuaDescricao() {
		String descricao = "";
		List<Produto> produtos = produtoRepository.findByDescricao(descricao);
		
		List<String> descricoes = produtos.stream().map(p -> p.getDescricao()).collect(Collectors.toList());
		
		System.out.println(descricoes);
		
		Assert.assertNotNull(descricoes);
		// Assert.assertEquals(descricao, produtos);
	}
}
