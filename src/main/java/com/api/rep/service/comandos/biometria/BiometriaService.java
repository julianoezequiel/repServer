package com.api.rep.service.comandos.biometria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.api.rep.dto.comandos.FPIDDTO;
import com.api.rep.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class BiometriaService extends ApiService {

	public void receber(RequestEntity<InputStreamResource> entity, Object repAutenticado) {
		LOGGER.info("Biometria Recebida.");
		try {
			InputStream data = null;
			data = entity.getBody().getInputStream();

			System.out.println(getStringFromInputStream(data));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				System.out.println(line.getBytes());
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

		return sb.toString();

	}

	public void receberListaBio(FPIDDTO fpiddto, Object repAutenticado) throws JsonProcessingException {
		if (fpiddto != null && !fpiddto.getPisList().isEmpty()) {
			LOGGER.info("Total Funcionários :" + fpiddto.getPisList().size());
			LOGGER.info(this.getMapper().writeValueAsString(fpiddto));
		}

	}

}
