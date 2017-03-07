package com.api.rep.contantes;

public class CONSTANTES {

	public static final String CHAVE_COMUNICACAO_DEFAULT = "**COMUNICACAO**";
	public static final String AUTH_KEY = "InnerRepPlus";
	public static final String CPF_TESTE = "04752873982";

	public static final String URL_EMPREGADOR = "/restrict/empregador";
	public static final String URL_EMPREGADO = "/restrict/empregado";
	public static final String URL_COLETA = "/restrict/coleta";
	public static final String URL_BIOMETRIA = "/restrict/empregado/bio";
	public static final String URL_LISTA_BIOMETRIA = "/restrict/empregado/listabio";
	public static final String URL_CONFIG = "/restrict/config";
	public static final String URL_INFO = "/restrict/info";
	public static final String URL_RELOGIO = "/restrict/relogio";

	public static final String URL_AUTH = "/auth";
	public static final String URL_STATUS = "/restrict/status";

	public enum TIPO_CMD {
		CMD_NENHUM() {// 0
			@Override
			public String getUrl() {
				return null;
			}
		},
		EMPREGADOR() {// 1
			@Override
			public String getUrl() {
				return URL_EMPREGADOR;
			}
		},
		EMPREGADO() {// 2
			@Override
			public String getUrl() {
				return URL_EMPREGADO;
			}
		},
		COLETA() {// 3
			@Override
			public String getUrl() {
				return URL_COLETA;
			}
		},
		BIOMETRIA() {// 4
			@Override
			public String getUrl() {
				return URL_BIOMETRIA;
			}
		},
		LISTA_BIOMETRIA() {// 5 pis dos funcionario bio
			@Override
			public String getUrl() {
				return URL_LISTA_BIOMETRIA;
			}
		},
		CONFIG() {// 6
			@Override
			public String getUrl() {
				return URL_CONFIG;
			}
		},
		INFO() {// 7
			@Override
			public String getUrl() {
				return URL_INFO;
			}
		},
		RELOGIO() {// 8
			@Override
			public String getUrl() {
				return URL_RELOGIO;
			}
		};

		public abstract String getUrl();

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
	}

	public enum TIPO_OPERACAO {
		NENHUMA, ENVIAR, RECEBER, EXCLUIR;

		public static TIPO_OPERACAO get(Integer tipo) {
			if (tipo != null) {
				for (TIPO_OPERACAO operacao : values()) {
					if (operacao.ordinal() == tipo) {
						return operacao;
					}
				}
			}
			return null;
		}

	}

	public enum TIPOS_NSR {
		TRAILER, // 0
		CABECALHO, // 1
		ALTERACAO_INCLUSAO_EMPRESA, // 2
		REGISTRO_PONTO, // 3
		AJUSTE_RELOGIO, // 4
		ALTERACAO_INCLUSAO_EMPREGADO, // 5
		EVENTOS_SENSIVEIS// 6
	}

	public enum STATUS_COM_REP {
		OK, // 0
		FALHA, // 1
		NAO_AUTORIZADO, // 2
		FALHA_JSON, // 3
		DADOS_INVALIDOS, // 4
		TAMANHO_DADOS_EXCEDIDO, // 5
		TAM_BUFFER_EXCEDIDO, // 6
		COMANDO_INVALIDO, // 7
		EMPRESA_NAO_CADASTRADA, // 8
		BASE_CHEIA, //9
		MEMORIA_CHEIA, // 10
		CADASTRO_SUCESSO, // 11
		ALTERADO_SUCESSO, // 12
		NAO_ALTERADO,//13 
		OCUPADO,// 14
		EMPREGADO_NAO_CAD;

		public static STATUS_COM_REP get(Integer tipo) {
			if (tipo != null) {
				for (STATUS_COM_REP operacao : values()) {
					if (operacao.ordinal() == tipo) {
						return operacao;
					}
				}
			}
			return null;
		}
	}

}
