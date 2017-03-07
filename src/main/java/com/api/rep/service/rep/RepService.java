package com.api.rep.service.rep;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dao.RepRepository;
import com.api.rep.dto.comunicacao.RepDTO;
import com.api.rep.entity.Rep;
import com.api.rep.service.ServiceException;

@Service
public class RepService {

	@Autowired
	private RepRepository repRepository;

	// busca todos os rep existentes na base
	public Collection<RepDTO> buscarTodos() {
		Collection<Rep> reps = repRepository.findAll();
		Collection<RepDTO> listaDTO = new ArrayList<>();
		reps.stream().forEach(rep -> {
			listaDTO.add(new RepDTO(rep));
		});
		return listaDTO;
	}

	public Rep buscarPorNumeroSerie(String numSerie) {
		return this.repRepository.buscarPorNumeroSerie(numSerie);
	}

	// inclui um rep na base
	public RepDTO salvar(RepDTO repDTO) throws ServiceException {
		if (repDTO.getNumeroSerie() == null) {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Número de série obrigatório");
		}
		if (this.buscarPorNumeroSerie(repDTO.getNumeroSerie()) != null) {
			throw new ServiceException(HttpStatus.PRECONDITION_FAILED, "Número de série já cadastrado");
		}
		return new RepDTO(this.repRepository.save(repDTO.getRep()));
	}

	public Rep salvar(Rep rep) {
		return this.repRepository.save(rep);
	}

	public String excluir(Integer id) throws ServiceException {
		Rep rep = this.repRepository.findOne(id);
		if (rep != null) {
			this.repRepository.delete(rep);
			return "Rep excluído com sucesso";
		} else {
			throw new ServiceException(HttpStatus.NO_CONTENT, "Rep não cadastrado");
		}
	}

}
