package com.bugsbuster.projectCaptainTech.service;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.bugsbuster.projectCaptainTech.model.Transferencia;
import com.bugsbuster.projectCaptainTech.repository.TransferenciaRepository;

@Service
public class TransferenciaService implements InterfaceTransferenciaService{
	@Autowired
	TransferenciaRepository tranRepo;

	@Override
	public Iterable<Transferencia> obterTodos() {
		return this.tranRepo.findAll();
	}

	@Override
	public Iterable<Transferencia> obterPorDestino(int id) {
		return  this.tranRepo.findByDestino(id);
	}

	@Override
	public Transferencia criarTransferencia(Transferencia tran) {
<<<<<<< HEAD
		return this.tranRepo.save(tran);
=======
		Conta origem = contaRepo.getById(tran.getContaOrigem().getId_conta());
		Conta destino = contaRepo.getById(tran.getContaDestino().getId_conta());
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
		
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
	}

	@Override
	public Iterable<Transferencia> obterPorOrigem(int id) {
		return this.tranRepo.findByOrigem(id);
	}

	@Override
	public Iterable<Transferencia> obterPorConta(int id) {
		return this.tranRepo.findHistConta(id);
	}
<<<<<<< HEAD

=======
	
	public Iterable<Transferencia> obterHistOrdenado(int conta){
		return this.tranRepo.findHistOrdenado(conta);
	}
>>>>>>> c42bac9213b577e656297d032572bcc6423a7875
}
