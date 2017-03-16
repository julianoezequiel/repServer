package com.api.rep.service.comandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.api.rep.entity.Rep;
import com.api.rep.entity.Tarefa;
import com.api.rep.service.ApiService;

@Service
public class AtualizarFirmwareService extends ApiService {

	public HashMap<String, Object> enviarFirmware(Integer nsu, Rep repAutenticado) {

		List<Tarefa> tarefas = this.getTarefaRepository().buscarPorNsu(nsu);
		InputStreamResource isr = null;
		HashMap<String, Object> map = new HashMap<>();

		if (!tarefas.isEmpty()) {

			try {
				InputStream inputStream;
				inputStream = new FileInputStream("c:\\InnerRepPlusV3.IR1");

				isr = new InputStreamResource(inputStream);
				map.put("fw", isr);
				map.put("tamanho", (long) inputStream.available());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public HashMap<String, Object> enviarPaginas(Integer nsu, Rep repAutenticado) {

		List<Tarefa> tarefas = this.getTarefaRepository().buscarPorNsu(nsu);
		InputStreamResource isr = null;
		HashMap<String, Object> map = new HashMap<>();

		if (!tarefas.isEmpty()) {

			try {
				InputStream inputStream;
				inputStream = new FileInputStream("C:\\InnerRep\\versao\\PaginasRep.RB1");

				isr = new InputStreamResource(inputStream);
				map.put("paginas", isr);
				map.put("tamanho", (long) inputStream.available());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}
