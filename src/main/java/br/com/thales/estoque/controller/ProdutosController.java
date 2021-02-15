package br.com.thales.estoque.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.thales.estoque.controller.dto.ProdutoDto;
import br.com.thales.estoque.controller.form.ProdutoForm;
import br.com.thales.estoque.model.Produto;
import br.com.thales.estoque.repository.ProdutoRepository;

@Controller
@RequestMapping("/home/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;

	// caso seja passado um query parameter, faremos o findByDescricao, caso nao
	// seja passado
	// nenhum query parameter, ira executar o findAll normalmente
	@GetMapping
	public String lista(String descricao, Model model) {
		
		if (descricao != null) {
			List<Produto> produtos = produtoRepository.findByDescricao(descricao);
//			ProdutoDto.converter(produtos);
			model.addAttribute("produtos", produtos);
			return "home";
		}
		List<Produto> produtos = produtoRepository.findAll();
		//ProdutoDto.converter(produtos);
		model.addAttribute("produtos", produtos);
		
		return "home";
	}
	
	@GetMapping("/novo")
	public String novo(ProdutoForm form) {
		return "novo";
	}

	@PostMapping("/novoProduto")
	@Transactional
	public String cadastra(@Valid ProdutoForm form, BindingResult result) {
		
		if (result.hasErrors()) {
			return "novo";
		}
		Produto produto = form.converter();
		produtoRepository.save(produto);

		// URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(produto.getId()).toUri();
		//return ResponseEntity.created(uri).body(new ProdutoDto(produto));
		return "redirect:/home/produtos";
	}

	// caso quisessemos usar outro nome para o parametro, podemos porem temos que
	// avisar o Spring:
	// public ProdutoDto detalha(@PathVariable("id") Long codigo)
	@GetMapping("/{descricao}")
	public String consultaPelaDescricao(@PathVariable String descricao, Model model) {
		List<Produto> produtos = produtoRepository.findByDescricao(descricao);
		
		model.addAttribute("produtos", produtos);
		return "home";

//		if (produto.isPresent()) {
//			return ResponseEntity.ok(new ProdutoDto(produto.get()));
//		}
//		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualiza(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {

		Optional<Produto> optional = produtoRepository.findById(id);

		if (optional.isPresent()) {
			Produto produto = form.atualiza(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return "redirect:/home/produtos";
		}
		
		return "redirect:/home/produtos";
	}

}
