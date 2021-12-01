package com.bugsbuster.projectCaptainTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
import com.bugsbuster.projectCaptainTech.service.PessoaFisicaServiceImpl;


@RestController
@RequestMapping(path = "/clientePF")
public class PessoaFisicaController {
	
	@Autowired
	public PessoaFisicaServiceImpl pessoaFisicaService;
	
	@Autowired
	public EnderecoRepository enderecoRepository;
	
	@GetMapping
	public Iterable<PessoaFisica> obterTodos() {
		return this.pessoaFisicaService.obterTodos();
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaFisica> obterPorId(@PathVariable int id){
		PessoaFisica obj = this.pessoaFisicaService.obterPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public PessoaFisica criarPessoaFisica(@RequestBody PessoaFisica pf) {
		return this.pessoaFisicaService.criarPessoaFisica(pf);
		
	}
	
	@GetMapping(value = "/nome/{nome}")
	public Iterable<PessoaFisica> obterPorNome(@PathVariable String nome){
		return this.pessoaFisicaService.obterPorNome(nome);
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public Iterable<PessoaFisica> obterPorCpf(@PathVariable String cpf){
		return this.pessoaFisicaService.obterPorCpf(cpf);
	}
	
	@GetMapping(value = "/telefone/{telefone}")
	public Iterable<PessoaFisica> obterPorTelefone(@PathVariable String telefone){
		return this.pessoaFisicaService.obterPorTelefone(telefone);
	}
	
//	@GetMapping(value = "/datanascimento/{data}")
//	public Iterable<PessoaFisica> ObterPorDataNascimento(@PathVariable String data){
//		return this.pessoaFisicaService.ObterPorDataNascimento(data);
//	}
	
	@GetMapping(value = "/ocupacao/{ocupacao}")
	public Iterable<PessoaFisica> ObterPorOcupacao(@PathVariable String ocupacao){
		return this.pessoaFisicaService.ObterPorOcupacao(ocupacao);
	}
	
	@PostMapping(value="/mensagem")
	public String enviarMensagem(@RequestBody String mensagem) {
		return this.pessoaFisicaService.publishMessageToTpoic(mensagem);
	}
	
	@PutMapping(path= "/atualizar")
	public PessoaFisica atualizarPf(@RequestBody PessoaFisica pf) {
		return this.pessoaFisicaService.atualizar(pf);
	}
	
}
