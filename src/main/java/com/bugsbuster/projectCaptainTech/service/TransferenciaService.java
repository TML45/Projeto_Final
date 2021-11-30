package com.bugsbuster.projectCaptainTech.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugsbuster.projectCaptainTech.model.Conta;
import com.bugsbuster.projectCaptainTech.model.Transferencia;
import com.bugsbuster.projectCaptainTech.repository.ContaRepository;
import com.bugsbuster.projectCaptainTech.repository.TransferenciaRepository;

@Service
public class TransferenciaService implements InterfaceTransferenciaService {
	@Autowired
	TransferenciaRepository tranRepo;

	@Autowired
	ContaRepository contaRepo;

	@Override
	public Iterable<Transferencia> obterTodos() {
		System.out.println("Consultando todas as transferências realizadas ");
		return this.tranRepo.findAll();
	}

	@Override
	public Iterable<Transferencia> obterPorDestino(int id) {
		System.out.println("Consultando transferências por ID da conta destino  ... "+id);
		return this.tranRepo.findByDestino(id);
	}

	@Override
	public Transferencia criarTransferencia(Transferencia tran) {
		Conta origem = contaRepo.getById(tran.getContaOrigem().getId_conta());
		Conta destino = contaRepo.getById(tran.getContaDestino().getId_conta());
		System.out.println("cadastrando uma nova pessoa transferência");
		System.out.println("Conta origem "+origem.getId_conta() + "Para Conta destino "+destino.getId_conta());
		System.out.println("o valor transferido é de "+tran.getValor());
		if(origem.getCliente().getAtivo() && destino.getCliente().getAtivo()) {
			if(origem.getSaldo() - tran.getValor() >= -200.0) {
				BigDecimal or = BigDecimal.valueOf(origem.getSaldo() - tran.getValor());
			    or = or.setScale(2, RoundingMode.HALF_UP);
				origem.setSaldo(or.doubleValue());
				BigDecimal dest = BigDecimal.valueOf(destino.getSaldo() + tran.getValor());
			    dest = dest.setScale(2, RoundingMode.HALF_UP);
				destino.setSaldo(dest.doubleValue());
				contaRepo.save(origem); //postman não atualiza as tabelas de conta
				contaRepo.save(destino);
				return this.tranRepo.save(tran);
			}
		}
		return null;
		
	}

	@Override
	public Iterable<Transferencia> obterPorOrigem(int id) {
		System.out.println("Consultando transferências por ID da conta origem  ... "+id);
		return this.tranRepo.findByOrigem(id);
	}

	@Override
	public Iterable<Transferencia> obterPorConta(int id) {
		System.out.println("Consultando transferências por numero de conta  ... "+id);
		return this.tranRepo.findHistConta(id);
	}
	
	public Iterable<Transferencia> obterHistOrdenado(int conta){
		System.out.println("Consultando as ultimas transferências por conta. Conta pesquisada:: "+conta);
		return this.tranRepo.findHistOrdenado(conta);
	}
}