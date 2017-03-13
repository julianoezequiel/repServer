package com.api.rep.service;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dto.comandos.BiometriaCmd;
import com.api.rep.dto.comunicacao.ComandoDeEnvio;
import com.api.rep.entity.Coleta;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Empregador;
import com.api.rep.service.tarefa.CmdHandler;

public class ComandosTeste {

	/**
	 * Envia um empregador para o REP
	 * 
	 * @return
	 */
	public static ComandoDeEnvio enviarEmpregador() {

		ComandoDeEnvio pendenciaDTO = new ComandoDeEnvio();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.settCmd(CmdHandler.TIPO_CMD.EMPREGADOR.ordinal());
		pendenciaDTO.settOp(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
		pendenciaDTO.setUrl(CmdHandler.TIPO_CMD.EMPREGADOR.getUrl());

		Empregador empregador = new Empregador();
		empregador.setEmpregadorCei("11111111111");
		empregador.setEmpregadorIdent("121321321321");
		empregador.setEmpregadorLocal("Local");
		empregador.setEmpregadorRazao("Razao");
		empregador.setEmpregadorTipoIdent("1");

		pendenciaDTO.setdCmd(empregador.toEmpregadorDTO());

		return pendenciaDTO;
	}

	/**
	 * Solicita ao Rep o empregador
	 * 
	 * @return
	 */
	public static ComandoDeEnvio solicitarEmpregador() {
		ComandoDeEnvio pendenciaDTO = new ComandoDeEnvio();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.settCmd(CmdHandler.TIPO_CMD.EMPREGADOR.ordinal());
		pendenciaDTO.settOp(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CmdHandler.TIPO_CMD.EMPREGADOR.getUrl());

		return pendenciaDTO;

	}

	/**
	 * Solicita uma coleta por faixa de NSR
	 * 
	 * @return
	 */
	public static ComandoDeEnvio solicitarColetaPorNumNSR() {

		ComandoDeEnvio pendenciaDTO = new ComandoDeEnvio();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.settCmd(CmdHandler.TIPO_CMD.COLETA.ordinal());
		pendenciaDTO.settOp(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CmdHandler.TIPO_CMD.COLETA.getUrl());

		Coleta coleta = new Coleta();
		coleta.setColetaNsrFim(1294820);
		coleta.setColetaNsrInicio(1294820 - 200);

		pendenciaDTO.setdCmd(coleta.toColetaCmd());

		return pendenciaDTO;

	}

	public static ComandoDeEnvio solictarBiometria() {

		ComandoDeEnvio pendenciaDTO = new ComandoDeEnvio();
		pendenciaDTO.setCpf(CONSTANTES.CPF_TESTE);
		pendenciaDTO.setNsu(1000);
		pendenciaDTO.settCmd(CmdHandler.TIPO_CMD.BIOMETRIA.ordinal());
		pendenciaDTO.settOp(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		pendenciaDTO.setUrl(CmdHandler.TIPO_CMD.BIOMETRIA.getUrl());

		BiometriaCmd biometriaCmd = new BiometriaCmd();
		biometriaCmd.setPis("");

		pendenciaDTO.setdCmd(biometriaCmd);

		return pendenciaDTO;
	}

	/**
	 * Solicita para o Rep a lista de usuário que possuem Biometria
	 * 
	 * @return
	 */
	public static ComandoDeEnvio solicitarListaBio() {
		ComandoDeEnvio comandoDeEnvio = new ComandoDeEnvio();
		comandoDeEnvio.setCpf(CONSTANTES.CPF_TESTE);
		comandoDeEnvio.setNsu(1000);
		comandoDeEnvio.settCmd(CmdHandler.TIPO_CMD.LISTA_BIO.ordinal());
		comandoDeEnvio.settOp(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		comandoDeEnvio.setUrl(CmdHandler.TIPO_CMD.LISTA_BIO.getUrl());

		return comandoDeEnvio;

	}

	/**
	 * Envia um empregado(Funcionario) para o Rep
	 * 
	 * @return
	 */
	public static ComandoDeEnvio enviarEmpregado() {

		ComandoDeEnvio comandoDeEnvio = new ComandoDeEnvio();
		comandoDeEnvio.setCpf(CONSTANTES.CPF_TESTE);
		comandoDeEnvio.setNsu(1000);
		comandoDeEnvio.settCmd(CmdHandler.TIPO_CMD.EMPREGADO.ordinal());
		comandoDeEnvio.settOp(CONSTANTES.TIPO_OPERACAO.ENVIAR.ordinal());
		comandoDeEnvio.setUrl(CmdHandler.TIPO_CMD.EMPREGADO.getUrl());

		Empregado empregado = new Empregado();

		empregado.setEmpregadoCartaoBarras("1111111111");
		empregado.setEmpregadoCartaoProx("222222222");
		empregado.setEmpregadoCartaoTeclado("333333333");
		empregado.setEmpregadoNome("Nome do funcionário");
		empregado.setEmpregadoNomeExibe("Nome de exibição");
		empregado.setEmpregadoPis("77777777777");
		empregado.setEmpregadoPossuiBio(false);

		comandoDeEnvio.setdCmd(empregado.toEmpregadoDTO());

		return comandoDeEnvio;

	}

	/**
	 * Solicita um empregado(Funcionario) para o Rep
	 * 
	 * @return
	 */
	public static ComandoDeEnvio solicitarEmpregado() {

		ComandoDeEnvio comandoDeEnvio = new ComandoDeEnvio();
		comandoDeEnvio.setCpf(CONSTANTES.CPF_TESTE);
		comandoDeEnvio.setNsu(1000);
		comandoDeEnvio.settCmd(CmdHandler.TIPO_CMD.EMPREGADO.ordinal());
		comandoDeEnvio.settOp(CONSTANTES.TIPO_OPERACAO.RECEBER.ordinal());
		comandoDeEnvio.setUrl(CmdHandler.TIPO_CMD.EMPREGADO.getUrl());

		Empregado empregado = new Empregado();
		empregado.setEmpregadoPis("77777777777");

		comandoDeEnvio.setdCmd(empregado.toEmpregadoDTO());

		return comandoDeEnvio;

	}

}
