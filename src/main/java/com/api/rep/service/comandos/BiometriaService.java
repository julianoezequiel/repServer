package com.api.rep.service.comandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.rep.dao.UsuarioBioRepository;
import com.api.rep.dto.comandos.ListaBio;
import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.entity.UsuarioBio;
import com.api.rep.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class BiometriaService extends ApiService {

	@Autowired
	private UsuarioBioRepository usuarioBioRepository;

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

	@SuppressWarnings("unused")
	private static byte[] getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString().getBytes();

	}

	public static HashMap<String, ListaBio> LISTA_BIO = new HashMap<>();

	public void receberListaBio(ListaBio listaBio, Rep repAutenticado) throws JsonProcessingException {
		if (listaBio != null && !listaBio.getPisList().isEmpty()) {
			LISTA_BIO.put(repAutenticado.getNumeroSerie(), listaBio);
			LOGGER.info("Total Funcionários :" + listaBio.getPisList().size());
			LOGGER.info(this.getMapper().writeValueAsString(listaBio));
		}

	}

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

}
