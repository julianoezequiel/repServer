package com.api.rep.service.comandos;

import org.springframework.stereotype.Component;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comunicacao.RespostaSevidorDTO;
import com.api.rep.entity.Rep;
import com.api.rep.service.ApiService;
import com.api.rep.service.TratarResposta;

@Component
public class CmdHandler {

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
		LISTA_EMPREGADO_DUMP() {// 4
			@Override
			public String getUrl() {
				return CONSTANTES.URL_LISTA_EMPREGADO_DUMPING;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		COLETA() {// 5
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
		BIOMETRIA() {// 6
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
		LISTA_BIO() {// 7 pis dos funcionario bio
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
		CONFIG_SENHA() {// 8
			@Override
			public String getUrl() {
				return CONSTANTES.URL_CONFIG_SENHA;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		CONFIG_REDE() {// 9
			@Override
			public String getUrl() {
				return CONSTANTES.URL_CONFIG_REDE;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		CONFIG_CARTOES() {// 10
			@Override
			public String getUrl() {
				return CONSTANTES.URL_CONFIG_CARTOES;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		CONFIG_HORARIO_VERAO() {// 11
			@Override
			public String getUrl() {
				return CONSTANTES.URL_CONFIG_HORARIO_VERAO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		CONFIG_RELOGIO() {// 12
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

		},
		INFO() {// 13
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
		IDENTFICACAO() {// 14
			@Override
			public String getUrl() {
				return CONSTANTES.URL_IDENTFICACAO;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		ATUALIZACAO_FW() {// 15
			@Override
			public String getUrl() {
				return CONSTANTES.URL_ATUALIZACAO_FW;
			}

			@Override
			public RespostaSevidorDTO tratarResposta(TratarResposta respostaRep, Rep repAutenticado,
					ApiService apiService) {
				// TODO Auto-generated method stub
				return null;
			}

		},
		ATUALIZACAO_PAGINAS() {// 16
			@Override
			public String getUrl() {
				return CONSTANTES.URL_ATUALIZACAO_PAGINAS;
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
