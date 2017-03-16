package com.api.rep.service.comandos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.NsrRepository;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.dto.comunicacao.StatusColetaDTO;
import com.api.rep.entity.Nsr;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ColetaNsrHandler;
import com.api.rep.service.ServiceException;

@Service
public class ColetaService extends ApiService {

	private static final String NUM_TRAILER = "999999999";
	private static final String NUM_CABECALHO = "000000000";

	@Autowired
	private NsrRepository nsrRepository;

	private Boolean cancelarColeta = false;

	@Value("${coleta.auto}")
	Boolean coletaAuto;

	// recebe o NSR
	public void coletaNsr(String registros, Rep rep) {

		this.setRep(rep);
		Nsr nsr = null;
		String[] registro = registros.split("\n");
		for (int i = 0; i < registro.length; i++) {

			String nsrRegistro = registro[i];

			Integer numNsr = ColetaNsrHandler.getNumNsr(nsrRegistro);

			if (!numNsr.equals(NUM_CABECALHO) && !numNsr.equals(NUM_TRAILER)) {
				try {

					nsr = this.nsrRepository.buscarPorNumNsr(numNsr);

					if (nsr == null) {
						if (coletaAuto) {
							nsr = ColetaNsrHandler.NSR_HANDLER.get(nsrRegistro).convert(nsrRegistro, this);
							nsr.setRepId(rep);
							this.nsrRepository.saveAndFlush(nsr);
						}
						LOGGER.info("Novo NSR Coletado :" + registro[i].replace("\r", ""));
					} else {
						LOGGER.info("NSR Já coletado :" + registro[i].replace("\r", ""));
					}

				} catch (Exception e) {
					System.err.println(e);
				}

			}
		}
		if (nsr != null && nsr.getNumeroNsr() != null) {
			rep.setUltimoNsr(nsr.getNumeroNsr());
			this.getRepService().salvar(rep);
		}
	}

	public RespostaSevidorDTO receber(String registros, Rep rep) throws ServiceException {
		LOGGER.info("Inicio do recebimento do coleta");
		if (rep == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não autorizado");
		}
		// busca a referencia correta do rep
		rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());

		if (rep != null) {
			// coleta os registros
			if (cancelarColeta) {
				cancelarColeta = false;
				throw new ServiceException(HttpStatus.RESET_CONTENT, "cancelado pelo usuário");
			} else {
				coletaNsr(registros, rep);
				LOGGER.info("Término do recebimento do coleta");
				return new RespostaSevidorDTO(HttpStatus.OK);
			}
		} else {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

	}

	public RespostaSevidorDTO statusColeta(StatusColetaDTO statusColetaDTO, Rep repAutenticado)
			throws ServiceException {
		LOGGER.info("Status da coleta : " + statusColetaDTO.getRegistrosColeto() + " Rep : "
				+ repAutenticado.getNumeroSerie());
		if (cancelarColeta) {
			cancelarColeta = false;
			throw new ServiceException(HttpStatus.RESET_CONTENT, "cancelado pelo usuário");
		} else {
			return new RespostaSevidorDTO(HttpStatus.OK);
		}
	}

	public Collection<Nsr> buscarNsrPorRep(Rep rep) throws ServiceException {
		if (rep != null) {
			rep = this.getRepService().buscarPorNumeroSerie(rep.getNumeroSerie());
			return this.nsrRepository.buscarPorRep(rep);
		} else {
			throw new ServiceException(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	public Long excluirTodos(Rep repAutenticado) throws ServiceException {
		return this.nsrRepository.removeByrepId(this.getRepPorNumeroSerie(repAutenticado));
	}

	public Long total(Rep repAutenticado) throws ServiceException {
		this.setRep(this.getRepPorNumeroSerie(repAutenticado));
		Nsr nsr = new Nsr();
		nsr.setRepId(this.getRep());
		return this.nsrRepository.count(Example.of(nsr));
	}

	public Boolean cancelar(Rep repAutenticado) {
		cancelarColeta = true;
		return cancelarColeta;
	}

}
