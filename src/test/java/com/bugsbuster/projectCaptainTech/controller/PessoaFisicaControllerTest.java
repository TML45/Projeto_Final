package com.bugsbuster.projectCaptainTech.controller;

//import io.restassured.module.mockmvc.RestAssuredMockMvc;
// static para usar os metodos sem a descrição inicial
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.bugsbuster.projectCaptainTech.model.Endereco;
import com.bugsbuster.projectCaptainTech.model.PessoaFisica;
import com.bugsbuster.projectCaptainTech.repository.EnderecoRepository;
import com.bugsbuster.projectCaptainTech.service.ContaService;
import com.bugsbuster.projectCaptainTech.service.PessoaFisicaServiceImpl;
import com.bugsbuster.projectCaptainTech.service.PessoaJuridicaServiceImpl;
import com.bugsbuster.projectCaptainTech.service.TransferenciaService;

import io.restassured.http.ContentType;

@WebMvcTest
public class PessoaFisicaControllerTest {
	
	@MockBean
	private PessoaFisicaController pessoaFisicaController;
	
	
	@MockBean
	private PessoaJuridicaServiceImpl pj;
	
	@MockBean
	private TransferenciaService ts;
	
	
	@MockBean
	EnderecoRepository endereco;
	
	@MockBean
	ContaService contaservice;
	
	@MockBean
	private PessoaFisicaServiceImpl pessoaFisicaService;
	
	@BeforeEach
	public void setup() {
		//standaloneSetup(this.pessoaFisicaController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarPessoaFisica() throws ParseException {
		Endereco novoEndereco = this.endereco.getById(1);
		when(this.pessoaFisicaService.obterPorId(1))
			.thenReturn(new PessoaFisica("teste2021@email.com","(51)99740 2000" ,
					novoEndereco, "Cliente Teste Unitario", "00432518054", "23/10/1984", "desenvolvedor"));
		
		
		given()
			.accept(ContentType.JSON)
			.when()
				.get("/clientePF")
			.then()
				.statusCode(HttpStatus.OK.value());
	}

}
