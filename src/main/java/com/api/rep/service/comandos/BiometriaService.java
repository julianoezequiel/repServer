package com.api.rep.service.comandos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.rep.contantes.CONSTANTES;
import com.api.rep.dao.UsuarioBioRepository;
import com.api.rep.dto.comandos.ListaBio;
import com.api.rep.entity.Empregado;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.entity.UsuarioBio;
import com.api.rep.service.ApiService;
import com.api.rep.service.ServiceException;
import com.api.rep.service.tarefa.TarefaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class BiometriaService extends ApiService {

	@Autowired
	private UsuarioBioRepository usuarioBioRepository;

	@Autowired
	private TarefaService tarefaService;

	/**
	 * Variável para armazenar a lista Bio
	 */
	public static HashMap<String, ListaBio> LISTA_BIO = new HashMap<>();

	/**
	 * Recebe a biometria do funcionário e salva na base de dados
	 * 
	 * @param entity
	 * @param repAutenticado
	 * @param nsu
	 */
	public void receber(MultipartFile entity, Object repAutenticado, Integer nsu) {
		LOGGER.info("Biometria Recebida.");
		List<Tarefa> t = this.getTarefaRepository().buscarPorNsu(nsu);
		UsuarioBio usuarioBio;
		if (!t.isEmpty()) {
			try {
				Tarefa tarefa = t.iterator().next();
				if (tarefa.getEmpregadoId() != null) {
					InputStream data = null;
					data = entity.getInputStream();
					byte[] template = IOUtils.toByteArray(data); // getStringFromInputStream(data);
					System.out.println(template);

					usuarioBio = this.usuarioBioRepository.buscarPorPis(tarefa.getEmpregadoId().getEmpregadoPis());
					if (usuarioBio == null) {
						usuarioBio = new UsuarioBio();
						usuarioBio.setPis(tarefa.getEmpregadoId().getEmpregadoPis());
					}

					usuarioBio.setTemplate(template);

					this.usuarioBioRepository.saveAndFlush(usuarioBio);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Recebe a lista biometrica e armazena em memoria
	 * 
	 * @param listaBio
	 * @param repAutenticado
	 * @throws JsonProcessingException
	 */
	public void receberListaBio(ListaBio listaBio, Rep repAutenticado) throws JsonProcessingException {
		if (listaBio != null && !listaBio.getPisList().isEmpty()) {
			LISTA_BIO.put(repAutenticado.getNumeroSerie(), listaBio);
			LOGGER.info("Total Funcionários :" + listaBio.getPisList().size());
			LOGGER.info(this.getMapper().writeValueAsString(listaBio));
		}

	}

	/**
	 * Envia a biometria do funionário para o Rep
	 * 
	 * @param nsu
	 * @param repAutenticado
	 * @return
	 */
	public HashMap<String, Object> enviar(Integer nsu, Rep repAutenticado) {

		HashMap<String, Object> map = new HashMap<>();

		InputStreamResource isr = null;

		List<Tarefa> tarefas = this.getTarefaRepository().buscarPorNsu(nsu);
		UsuarioBio usuarioBio;
		if (!tarefas.isEmpty()) {
			Tarefa tarefa = tarefas.iterator().next();
			if (tarefa.getEmpregadoId() != null) {
				usuarioBio = this.usuarioBioRepository.buscarPorPis(tarefa.getEmpregadoId().getEmpregadoPis());
				if (usuarioBio != null) {
					try {
						File convFile = File.createTempFile("arquivo", ".txt");
						FileOutputStream fos = new FileOutputStream(convFile);
						fos.write(usuarioBio.getTemplate());
						fos.close();
						InputStream inputStream;
						inputStream = new FileInputStream(convFile);

						isr = new InputStreamResource(inputStream);
						map.put("arquivo", isr);
						map.put("tamanho", convFile.length());
						convFile.deleteOnExit();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return map;
	}

	/**
	 * Retorna a lista Bio armazenada em memória
	 * 
	 * @param rep
	 * @return
	 */
	public ListaBio getListaBio(Rep rep) {
		if (BiometriaService.LISTA_BIO.containsKey(rep.getNumeroSerie())) {
			return BiometriaService.LISTA_BIO.get(rep.getNumeroSerie());
		}
		return null;
	}

	/**
	 * Recebe a lista de usuários biometrico e agenda o recebimento das digitais
	 * dos usuário
	 * 
	 * @param listaBio
	 * @param repAutenticado
	 */
	public void receberListaBioAgendarDigitais(ListaBio listaBio, Rep repAutenticado) {

		if (listaBio != null && listaBio.getPisList() != null) {
			listaBio.getPisList().stream().forEach(pis -> {
				try {
					Tarefa tarefa = tarefaService.tarefaTeste(CONSTANTES.TIPO_OPERACAO.RECEBER.name(), repAutenticado);
					tarefa.setTipoTarefa(CmdHandler.TIPO_CMD.BIOMETRIA.ordinal());
					Empregado empregado = new Empregado(pis);
					tarefa.setEmpregadoId(empregado);
					this.tarefaService.salvar(tarefa);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

	}

}
