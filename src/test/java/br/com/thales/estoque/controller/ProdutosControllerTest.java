package br.com.thales.estoque.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.thales.estoque.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProdutosControllerTest {
	
	@MockBean
	private ProdutoRepository produtoRepository;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaRetornar404CasoOIdDoProdutoInformadoNaoExista() throws Exception {
		URI uri = new URI("localhost:8080/produtos/");
		String id = "/999";

		mockMvc.perform(MockMvcRequestBuilders.get(uri).pathInfo(id)).andExpect(MockMvcResultMatchers.status().is(404));
		
	}

}
