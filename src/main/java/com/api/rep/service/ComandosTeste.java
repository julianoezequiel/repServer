package com.api.rep.service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.BiometriaDTO;
import com.api.rep.dto.comunicacao.TarefaDTO;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;

public class ComandosTeste {

	/**
	 * Envia um empregador para o REP
	 * 
	 * @return
	 */
	public static TarefaDTO enviarEmpregador() {

		TarefaDTO pendenciaDTO = new TarefaDTO();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.setTipoComando(CONSTANTES.TIPO_CMD.EMPREGADOR.ordinal());
		pendenciaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
		pendenciaDTO.setUrl(CONSTANTES.TIPO_CMD.EMPREGADOR.getUrl());

		Empregador empregador = new Empregador();
		empregador.setEmpregadorCei("11111111111");
		empregador.setEmpregadorIdent("121321321321");
		empregador.setEmpregadorLocal("Local");
		empregador.setEmpregadorRazao("Razao");
		empregador.setEmpregadorTipoIdent("1");

		pendenciaDTO.setDadosComando(empregador.toEmpregadorDTO());

		return pendenciaDTO;
	}

	/**
	 * Solicita ao Rep o empregador
	 * 
	 * @return
	 */
	public static TarefaDTO solicitarEmpregador() {
		TarefaDTO pendenciaDTO = new TarefaDTO();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.setTipoComando(CONSTANTES.TIPO_CMD.EMPREGADOR.ordinal());
		pendenciaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CONSTANTES.TIPO_CMD.EMPREGADOR.getUrl());

		return pendenciaDTO;

	}

	/**
	 * Solicita uma coleta por faixa de NSR
	 * 
	 * @return
	 */
	public static TarefaDTO solicitarColetaPorNumNSR() {

		TarefaDTO pendenciaDTO = new TarefaDTO();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.setTipoComando(CONSTANTES.TIPO_CMD.COLETA.ordinal());
		pendenciaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CONSTANTES.TIPO_CMD.COLETA.getUrl());

		Coleta coleta = new Coleta();
		coleta.setColetaNsrFim(1294820);
		coleta.setColetaNsrInicio(1294820 - 200);

		pendenciaDTO.setDadosComando(coleta.toColetaDTO());

		return pendenciaDTO;

	}
	

	public static TarefaDTO solictarBiometria() {

		TarefaDTO pendenciaDTO = new TarefaDTO();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.setTipoComando(CONSTANTES.TIPO_CMD.BIOMETRIA.ordinal());
		pendenciaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CONSTANTES.TIPO_CMD.BIOMETRIA.getUrl());

		BiometriaDTO biometriaDTO = new BiometriaDTO();
		biometriaDTO.setPis("");

		pendenciaDTO.setDadosComando(biometriaDTO);

		return pendenciaDTO;
	}

	/**
	 * Solicita para o Rep a lista de usuário que possuem Biometria
	 * 
	 * @return
	 */
	public static TarefaDTO solicitarListaBio() {
		TarefaDTO tarefaDTO = new TarefaDTO();
		tarefaDTO.setCpf(CONSTANTES.CPF_TESTE);
		tarefaDTO.setNsu(1000);
		tarefaDTO.setTipoComando(CONSTANTES.TIPO_CMD.LISTA_BIOMETRIA.ordinal());
		tarefaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefaDTO.setUrl(CONSTANTES.TIPO_CMD.LISTA_BIOMETRIA.getUrl());
		
		return tarefaDTO;

	}

	/**
	 * Envia um empregado(Funcionario) para o Rep
	 * 
	 * @return
	 */
	public static TarefaDTO enviarEmpregado() {

		TarefaDTO tarefaDTO = new TarefaDTO();
		tarefaDTO.setCpf(CONSTANTES.CPF_TESTE);
		tarefaDTO.setNsu(1000);
		tarefaDTO.setTipoComando(CONSTANTES.TIPO_CMD.EMPREGADO.ordinal());
		tarefaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
		tarefaDTO.setUrl(CONSTANTES.TIPO_CMD.EMPREGADO.getUrl());

		Empregado empregado = new Empregado();

		empregado.setEmpregadoCartaoBarras("1111111111");
		empregado.setEmpregadoCartaoProx("222222222");
		empregado.setEmpregadoCartaoTeclado("333333333");
		empregado.setEmpregadoNome("Nome do funcionário");
		empregado.setEmpregadoNomeExibe("Nome de exibição");
		empregado.setEmpregadoPis("77777777777");
		empregado.setEmpregadoPossuiBio(false);

		tarefaDTO.setDadosComando(empregado.toEmpregadoDTO());

		return tarefaDTO;

	}
	
	/**
	 * Solicita um empregado(Funcionario) para o Rep
	 * 
	 * @return
	 */
	public static TarefaDTO solicitarEmpregado() {

		TarefaDTO tarefaDTO = new TarefaDTO();
		tarefaDTO.setCpf(CONSTANTES.CPF_TESTE);
		tarefaDTO.setNsu(1000);
		tarefaDTO.setTipoComando(CONSTANTES.TIPO_CMD.EMPREGADO.ordinal());
		tarefaDTO.setTipoOperacao(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		tarefaDTO.setUrl(CONSTANTES.TIPO_CMD.EMPREGADO.getUrl());

		Empregado empregado = new Empregado();
		empregado.setEmpregadoPis("77777777777");

		tarefaDTO.setDadosComando(empregado.toEmpregadoDTO());
		
		return tarefaDTO;

	}
	

}
