package com.api.rep.service.auth;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.rep.dto.comunicacao.CriptoRnd;
import com.api.rep.dto.comunicacao.RepDTO;
import com.api.rep.dto.comunicacao.TokenDTO;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;
import com.api.rep.utils.JwtUtil;

@Service
public class AuthService extends ApiService {

	@Autowired
	private JwtUtil jwtUtil;

	private String token;

	private HashMap<String, CriptoRnd> map = new HashMap<>();

	/**
	 * Metodo para autenticar e criar o Token de acesso para o Rep.
	 * 
	 * @param repDTO
	 * @return TokenDTO
	 * @throws ServiceException
	 */
	public TokenDTO autenticar(RepDTO repDTO) throws ServiceException {

		// TODO: validar a criptografia
		if (repDTO.getSign() == null || !map.containsKey(repDTO.getNumeroSerie())) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		}

		
		LOGGER.info("Rep assinatura : " + repDTO.getSign());
		// Campo obrigatório
		if (repDTO == null || repDTO.getNumeroSerie() == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

		// busca o rep na base
		Rep rep = this.getRepService().buscarPorNumeroSerie(repDTO.getNumeroSerie());

		if (rep != null) {
			token = jwtUtil.generateToken(rep.getNumeroSerie());
		} else {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

		ApiService.LOGGER.info("Token criado : " + token);
		map.remove(repDTO.getNumeroSerie());

		return new TokenDTO(token, rep.getChaveComunicacao());
	}

	public CriptoRnd criptoRnd(RepDTO repDTO) throws ServiceException {
		if (repDTO.getNumeroSerie() == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}
		LOGGER.info("Rep número de Série : " + repDTO.getNumeroSerie());
		Rep rep = this.getRepService().buscarPorNumeroSerie(repDTO.getNumeroSerie());
		if (rep == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}
		CriptoRnd criptoRnd = new CriptoRnd();
		Random random = new Random();
		criptoRnd.setInfoRnd(new Long(random.nextLong()).toString());
		map.put(repDTO.getNumeroSerie(), criptoRnd);
		return criptoRnd;
	}

}
