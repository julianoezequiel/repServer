package com.api.rep.service.tarefa;

import org.springframework.stereotype.Component;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comunicacao.RespostaRepDTO;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.comandos.TratarResposta;
import com.api.rep.service.comandos.empregador.TratarRespostaEmpregador;

@Component
public class TarefaHandler {

	public enum TIPO_CMD {

		CMD_NENHUM() {// 0
			@Override
			public String getUrl() {
				return null;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		EMPREGADOR() {// 1
			@Override
			public String getUrl() {
				return CONSTANTES.URL_EMPREGADOR;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		EMPREGADO() {// 2
			@Override
			public String getUrl() {
				return CONSTANTES.URL_EMPREGADO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		LISTA_EMPREGADO() {// 3
			@Override
			public String getUrl() {
				return CONSTANTES.URL_LISTA_EMPREGADO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		COLETA() {// 4
			@Override
			public String getUrl() {
				return CONSTANTES.URL_COLETA;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		BIOMETRIA() {// 5
			@Override
			public String getUrl() {
				return CONSTANTES.URL_BIOMETRIA;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		LISTA_BIOMETRIA() {// 6 pis dos funcionario bio
			@Override
			public String getUrl() {
				return CONSTANTES.URL_LISTA_BIOMETRIA;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		CONFIG() {// 7
			@Override
			public String getUrl() {
				return CONSTANTES.URL_CONFIG;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		INFO() {// 8
			@Override
			public String getUrl() {
				return CONSTANTES.URL_INFO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		RELOGIO() {// 9
			@Override
			public String getUrl() {
				return CONSTANTES.URL_RELOGIO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		};

		public static TIPO_CMD get(Integer tipo) {
			if (tipo != null) {
				for (TIPO_CMD operacaocao : values()) {
					if (operacaocao.ordinal() == tipo) {
						return operacaocao;
					}
				}
			}
			return null;
		}

		public abstract String getUrl();

		public abstract RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
				ApiService apiService);
	}

}
