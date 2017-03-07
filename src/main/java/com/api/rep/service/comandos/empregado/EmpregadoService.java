package com.api.rep.service.comandos.empregado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.EmpregadoRespository;
import com.api.rep.dto.comandos.ComandoAbstract;
import com.api.rep.dto.comandos.EmpregadoDTO;
import com.api.rep.dto.comandos.EmpregadorDTO;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Tarefa;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;

@Service
public class EmpregadoService extends ApiService {

	@Autowired
	private EmpregadoRespository empregadoRespository;

	public Collection<Empregado> listar() {
		return this.empregadoRespository.findAll();
	}

	public Empregado buscarPorId(Integer id) {
		return this.empregadoRespository.findOne(id);
	}

	public String excluirPorId(Integer id) {
		// TODO: inserir nova Tarefa de envio para o REP
		this.empregadoRespository.delete(id);
		return "Empregado excluido com sucesso";
	}

	public Empregado salvar(Empregado empregado, Rep rep) throws ServiceException {

		// TODO: Somente inserir uma nova Tarefa se o empregado for diferente

		if (empregado.getEmpregadoPis() != null) {

			// se ja existir atualiza o registro
			Optional<Empregado> e = this.empregadoRespository.buscarPorPis(empregado.getEmpregadoPis());

			if (e.isPresent()) {
				empregado.setId(e.get().getId());
			}

			// buscao o Rep atual
			rep = this.getRepPorNumeroSerie(rep);

			// cria a Tarefa
			Tarefa tarefa = new Tarefa();
			tarefa.setCpf("04752873982");
			tarefa.setRepId(rep);
			tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
			tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADO.ordinal());

			// salva ou atualiza o empregado
			empregado = this.empregadoRespository.save(empregado);
			// vincula o empregado a pendencia
			tarefa.setEmpregadoId(empregado);
			// sava a pendencia
			this.getTarefaRepository().save(tarefa);

			return empregado;
		} else {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Empregado inválido");
		}

	}

	@Override
	public RespostaSevidorDTO validarRespostaRep(RespostaRepDTO respostaRep, Rep repAutenticado) {
		// TODO : Tratar a resposta de comando de forma especifica
		if (respostaRep.getStatus().stream()
				.anyMatch(s -> s == CONSTANTES.STATUS_COM_REP.EMPREGADO_NAO_CAD.ordinal())) {
			LOGGER.info("Empregado não cadastrado");
		}
		return super.validarRespostaRep(respostaRep, repAutenticado);
	}

	@Override
	public void receber(ComandoAbstract comandoAbstract, Rep rep) throws ServiceException {

		// TODO : Tratar os dados do comando de forma específica

		if (comandoAbstract instanceof EmpregadoDTO) {
			EmpregadoDTO empregadorDTO = (EmpregadoDTO) comandoAbstract;

			LOGGER.info("----------- Funcionário recebido --------------");
			LOGGER.info("Nome : " + empregadorDTO.getEmpregadoNome());
			LOGGER.info("Nome exibição : " + empregadorDTO.getEmpregadoNomeExibe());
			LOGGER.info("Pis : " + empregadorDTO.getEmpregadoPis());
			LOGGER.info("Número Teclado : " + empregadorDTO.getEmpregadoCartaoTeclado());
			LOGGER.info("Número prox : " + empregadorDTO.getEmpregadoCartaoProx());
			LOGGER.info("Número barras : " + empregadorDTO.getEmpregadoCartaoBarras());
			LOGGER.info("Possui Bio : " + empregadorDTO.getEmpregadoPossuiBio());

			Optional<Empregado> empregado = this.getEmpregadoRespository()
					.buscarPorPis(empregadorDTO.getEmpregadoPis());
			if (empregado.isPresent()) {
				Empregado empregado2 = empregadorDTO.toEmpregado();
				empregado2.setId(empregado.get().getId());
				this.getEmpregadoRespository().save(empregado2);
			}
		}

	}

}
