package com.bugsbuster.projectCaptainTech.service;

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
		return this.tranRepo.findAll();
	}

	@Override
	public Iterable<Transferencia> obterPorDestino(int id) {
		return this.tranRepo.findByDestino(id);
	}

	@Override
	public Transferencia criarTransferencia(Transferencia tran) {
		Conta origem = contaRepo.getById(tran.getContaOrigem().getId_conta());
		Conta destino = contaRepo.getById(tran.getContaDestino().getId_conta());

		if (origem.getCliente().getAtivo() && destino.getCliente().getAtivo()) {
			if (tran.getValor() - origem.getSaldo() <= 200) {
				origem.setSaldo(origem.getSaldo()-tran.getValor());
				destino.setSaldo(destino.getSaldo()+tran.getValor());
				return this.tranRepo.save(tran);
			}
		}
		return null;
	}

	@Override
	public Iterable<Transferencia> obterPorOrigem(int id) {
		return this.tranRepo.findByOrigem(id);
	}

	@Override
	public Iterable<Transferencia> obterPorConta(int id) {
		return this.tranRepo.findHistConta(id);
	}
}
