package br.com.thales.estoque;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @SpringBootTest serve para que o Spring inicialize o servidor e carregue os beans da aplicação durante a execução dos testes automatizados. */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstoqueApplicationTests {

	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}

}
