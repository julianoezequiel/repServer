package com.api.rep.service.comandos.empregador;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.EmpregadorRepository;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comandos.EmpregadorDTO;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Empregador;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class EmpregadorService extends ApiService {

	@Autowired
	private EmpregadorRepository empregadorRepository;

	public Collection<Empregador> listar() {
		return this.empregadorRepository.findAll();
	}

	public Empregador buscarPorId(Integer id) {
		return this.empregadorRepository.findOne(id);
	}

	public String excluirPorId(Integer id) {
		// TODO:inserir nova Tarefa de envio para o rep
		this.empregadorRepository.delete(id);
		return "Empregador excluido com sucesso";
	}

	public Optional<Empregador> buscarPorIndentificador(String identificador) {
		return this.empregadorRepository.buscarPorIndentificador(identificador);
	}

	public Empregador salvar(Empregador empregador, Rep rep) throws ServiceException {
		// TODO:validar todos os dados do empregador
		if (empregador.getEmpregadorIdent() != null) {

			rep = this.getRepPorNumeroSerie(rep);

			Empregador e = rep.getEmpregadorId();
			if (e != null) {
				empregador.setId(e.getId());
			}
			Tarefa tarefa = new Tarefa();
			tarefa.setCpf(CONSTANTES.CPF_TESTE);
			tarefa.setRepId(rep);
			tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
			tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADOR.ordinal());

			empregador = this.empregadorRepository.save(empregador);

			rep.setEmpregadorId(empregador);

			this.getRepService().salvar(rep);

			tarefa.setEmpregadorId(empregador);
			this.getTarefaRepository().save(tarefa);
			return empregador;
		} else {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Empregador Inválido");
		}
	}

	@Override
	public RespostaSevidorDTO validarRespostaRep(RespostaRepDTO respostaRep, Rep repAutenticado) {
		// TODO : Tratar a resposta de comando de forma especifica
		return super.validarRespostaRep(respostaRep, repAutenticado);
	}

	@Override
	public void receber(ComandoAbstract comandoAbstract, Rep rep) throws ServiceException {

		// TODO : Tratar os dados do comando de forma específica

		if (comandoAbstract instanceof EmpregadorDTO) {

			EmpregadorDTO empregadorDTO = (EmpregadorDTO) comandoAbstract;

			LOGGER.info("----------- Empresa recebida --------------");
			LOGGER.info("Razão : " + empregadorDTO.getEmpregadorRazao());
			LOGGER.info("Identificador : " + empregadorDTO.getEmpregadorIdent());
			LOGGER.info("Cei : " + empregadorDTO.getEmpregadorCei());
			LOGGER.info("Local : " + empregadorDTO.getEmpregadorLocal());
			LOGGER.info("Tipo ident : " + empregadorDTO.getEmpregadorTipoIdent());

			Optional<Empregador> empregador = this.buscarPorIndentificador(empregadorDTO.getEmpregadorIdent());

			if (empregador.isPresent()) {
				Empregador empregador2 = empregadorDTO.toEmpregador();
				empregador2.setId(empregador.get().getId());
				this.empregadorRepository.save(empregador2);
			}
		}

	}

}
