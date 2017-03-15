package com.api.rep.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

	/**
	 * Metodo para autenticar e criar o Token de acesso para o Rep.
	 * 
	 * @param repDTO
	 * @return TokenDTO
	 * @throws ServiceException
	 */
	public TokenDTO autenticar(RepDTO repDTO) throws ServiceException {

		// Campo obrigatório
		if (repDTO == null || repDTO.getNumeroSerie() == null) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

		// busca o rep na base
		Rep rep = this.getRepService().buscarPorNumeroSerie(repDTO.getNumeroSerie());

		if (rep != null) {
			// TODO: Validar a necessidade do Rep enviar mais um parametro para
			// realizar a autenticação
			token = jwtUtil.generateToken(rep.getNumeroSerie());
		} else {
			throw new ServiceException(HttpStatus.UNAUTHORIZED, "Rep não cadastrado");
		}

		ApiService.LOGGER.info("Token criado : " + token);

		return new TokenDTO(token, rep.getChaveComunicacao());
	}

}
