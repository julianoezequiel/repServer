package com.api.rep.service.comandos.coleta;

import java.util.Optional;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Nsr;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;
import com.api.rep.utils.Utils;

public class DecomporRegistro {

	private static Nsr nsr = new Nsr();

	public DecomporRegistro() {
		DecomporRegistro.nsr = new Nsr();
	}

	public enum CONVERTER_NSR {

		TIPO_NENHUM() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return null;
			}
		},
		TIPO_CABECALHO() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return null;
			}
		},
		TIPO_EMPREGADOR() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return tipoEmpregador(registro, apiService);
			}
		},
		TIPO_MARCACAO() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return tipoMarcacao(registro);
			}
		},
		TIPO_RELOGIO() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return tipoRelogio(registro, apiService);
			}
		},

		TIPO_EMPREGADO() {

			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return tipoEmpregado(registro, apiService);
			}
		},
		TIPO_EVENTOS() {
			@Override
			public Nsr convert(String registro, ApiService apiService) {
				return tipoEventosSensiveis(registro);
			}
		};

		public abstract Nsr convert(String registro, ApiService apiService);

		public static CONVERTER_NSR get(String registro) {
			Integer tipo = getTipoNsr(registro);
			if (tipo != null) {
				for (CONVERTER_NSR nsr : values()) {
					if (tipo.equals(nsr.ordinal())) {
						return nsr;
					}
				}
			}
			return null;
		}

	}

	public static Integer getNumNsr(String registro) {
		try {
			return Integer.parseInt(registro.substring(0, 9));
		} catch (Exception e) {
			return null;
		}
	}

	private static Integer getTipoNsr(String registro) {
		try {
			return Integer.parseInt(registro.substring(9, 10));
		} catch (Exception e) {
			return null;
		}
	}

	private static Nsr tipoEmpregador(String registro, ApiService apiService) {

		DecomporRegistro.nsr = new Nsr();
		DecomporRegistro.nsr.setNumeroNsr(getNumNsr(registro));
		DecomporRegistro.nsr.setTipo(getTipoNsr(registro));
		DecomporRegistro.nsr.setDataGravacao(Utils.converterStringParaData(registro.substring(10, 18)));
		DecomporRegistro.nsr.setHorarioGravacao(Utils.converterStringParaHora(registro.substring(18, 22)));
		DecomporRegistro.nsr.setCpfResponsavel(registro.substring(22, 36));
		DecomporRegistro.nsr.setTipoIndentificador(Integer.parseInt(registro.substring(36, 37)));
		DecomporRegistro.nsr.setCnpj_cpf(registro.substring(37, 51));
		DecomporRegistro.nsr.setCei(registro.substring(51, 63));
		DecomporRegistro.nsr.setRazaoSocial(registro.substring(63, 213));
		DecomporRegistro.nsr.setLocal(registro.substring(215, 313));
		
		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADOR.ordinal());
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefa.setRepId(apiService.getRep());

		apiService.getTarefaRepository().save(tarefa);

		return DecomporRegistro.nsr;

	}

	private static Nsr tipoMarcacao(String registro) {

		DecomporRegistro.nsr = new Nsr();
		DecomporRegistro.nsr.setNumeroNsr(getNumNsr(registro));
		DecomporRegistro.nsr.setTipo(getTipoNsr(registro));
		DecomporRegistro.nsr.setDataMarcacao(Utils.converterStringParaData(registro.substring(10, 18)));
		DecomporRegistro.nsr.setHorarioMarcacao(Utils.converterStringParaHora(registro.substring(18, 22)));
		DecomporRegistro.nsr.setPis(registro.substring(22, 34));

		return DecomporRegistro.nsr;
	}

	private static Nsr tipoRelogio(String registro, ApiService apiService) {

		DecomporRegistro.nsr = new Nsr();
		DecomporRegistro.nsr.setNumeroNsr(getNumNsr(registro));
		DecomporRegistro.nsr.setTipo(getTipoNsr(registro));
		DecomporRegistro.nsr.setDataAntesAjuste(Utils.converterStringParaData(registro.substring(10, 18)));
		DecomporRegistro.nsr.setHoraAntesAjuste(Utils.converterStringParaHora(registro.substring(18, 22)));
		DecomporRegistro.nsr.setDataAjustada(Utils.converterStringParaData(registro.substring(22, 30)));
		DecomporRegistro.nsr.setHoraAjustada(Utils.converterStringParaHora(registro.substring(30, 34)));
		DecomporRegistro.nsr.setCpfResponsavel(registro.substring(34, 45));

		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.RELOGIO.ordinal());
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefa.setRepId(apiService.getRep());

		// apiService.getTarefaRepository().save(tarefa);

		return DecomporRegistro.nsr;

	}

	private static Nsr tipoEmpregado(String registro, ApiService apiService) {

		DecomporRegistro.nsr = new Nsr();

		String pis = registro.substring(23, 35);

		DecomporRegistro.nsr.setNumeroNsr(getNumNsr(registro));
		DecomporRegistro.nsr.setTipo(getTipoNsr(registro));
		DecomporRegistro.nsr.setDataGravacao(Utils.converterStringParaData(registro.substring(10, 18)));
		DecomporRegistro.nsr.setHorarioGravacao(Utils.converterStringParaHora(registro.substring(18, 22)));
		DecomporRegistro.nsr.setTipoOperacao(registro.substring(22, 23));
		DecomporRegistro.nsr.setPis(pis);
		DecomporRegistro.nsr.setNomeEmpregado(registro.substring(35, 87));
		DecomporRegistro.nsr.setDadosEmpregado(registro.substring(87, 91));
		DecomporRegistro.nsr.setCpfResponsavel(registro.substring(91, 102));

		Tarefa tarefa = new Tarefa();
		tarefa.setCpf(CONSTANTES.CPF_TESTE);
		tarefa.setTipoTarefa(CONSTANTES.TIPO_CMD.EMPREGADO.ordinal());
		tarefa.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefa.setRepId(apiService.getRep());

		Optional<Empregado> empregado = apiService.getEmpregadoRespository().buscarPorPis(pis);

		// se for uma inclusao solicita o novo empregado
		if (DecomporRegistro.nsr.getTipoOperacao().equals("I")) {
			if (empregado.isPresent()) {
				tarefa.setEmpregadoId(empregado.get());
			} else {

				Empregado novoEmpregado = new Empregado();
				novoEmpregado.setEmpregadoPis(pis);
				apiService.getEmpregadoRespository().save(novoEmpregado);
				tarefa.setEmpregadoId(novoEmpregado);
				apiService.getTarefaRepository().save(tarefa);
			}
			// se fir uma exclusão e existir o regitro, solicita a exclusao da
			// base
		} else if (empregado.isPresent() && DecomporRegistro.nsr.getTipoOperacao().equals("E")) {
			apiService.getEmpregadoRespository().delete(empregado.get());
		}

		return DecomporRegistro.nsr;
	}

	private static Nsr tipoEventosSensiveis(String registro) {

		DecomporRegistro.nsr = new Nsr();
		DecomporRegistro.nsr.setNumeroNsr(getNumNsr(registro));
		DecomporRegistro.nsr.setTipo(getTipoNsr(registro));
		DecomporRegistro.nsr.setDataGravacao(Utils.converterStringParaData(registro.substring(10, 18)));
		DecomporRegistro.nsr.setHorarioGravacao(Utils.converterStringParaHora(registro.substring(18, 22)));
		DecomporRegistro.nsr.setTipoEvento(registro.substring(22, registro.length() - 1));

		return DecomporRegistro.nsr;
	}

}
