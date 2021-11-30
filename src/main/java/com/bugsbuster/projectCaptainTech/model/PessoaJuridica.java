package com.bugsbuster.projectCaptainTech.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PessoaJuridica extends Cliente{
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Campo Razão Social Vazio")
	@Size(min=3)
	private String razaoSocial;
	
	@Size(min=5, max=50)
	private String nomeFantasia;
	
	@NotNull(message = "Campo CNPJ Vazio")
	@Size(min=14, max=14)
	@Column(unique =  true)
	private String cnpj;
	
	@Size(min=3)
	@Column(unique = true)
	private String inscricaoEstadual;
	
	@NotNull(message = "Campo Data Vazio")
	@Past
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFundacao;
	
	public PessoaJuridica() {
		super();
	}
	
	public PessoaJuridica( @NotNull @Email @Size(min = 8) String email,
			@NotNull @Size(min = 14, max = 14) String telefone, Endereco endereco,
			@NotNull(message = "Campo Razão Social Vazio") @Size(min = 3) String razaoSocial,
			@NotNull(message = "Campo Nome Fantasia Vazio") @Size(min = 5, max = 50) String nomeFantasia,
			@NotNull(message = "Campo CNPJ Vazio") @Size(min = 14, max = 14) String cnpj,
			@NotNull(message = "Campo Inscrição Estual Vazio") @Size(min = 3) String inscricaoEstadual,
			@NotNull(message = "Campo Data Vazio") @Past String dataFundacao) throws ParseException {
		super(email, telefone, endereco);
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		setDataFundacao(dataFundacao);
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(String dataFundacao) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataFundacao = LocalDate.parse(dataFundacao, formatter);
	}
}