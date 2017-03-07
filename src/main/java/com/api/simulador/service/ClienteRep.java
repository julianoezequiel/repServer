package com.api.simulador.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.rep.dto.comunicacao.StatusDTO;
import com.api.rep.service.ServiceException;
import com.api.simulador.Simulador;
import com.api.simulador.entity.Iniciar;
import com.api.simulador.rest.SimuladorRestController;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClienteRep {

	public final static Logger LOGGER = LoggerFactory.getLogger(ClienteRep.class.getName());

	public static void execut() throws ServiceException {

		ObjectMapper mapper = new ObjectMapper();

		while (SimuladorRestController.init) {
			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost("http://alfa_crux:80/restrict/status/");
				postRequest.addHeader("accept", "application/json");
				postRequest.addHeader("Authorization",
						"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTExMjUxMjEyMTIxMjEyIiwiZXhwIjoxNDg3OTk1Njc4fQ.BRBUl1LHZsc3egMINjkpAMR3eOg7BsEp5_akvfIFVV8");

				postRequest.addHeader("content-type", "application/json");

				StatusDTO statusDTO = new StatusDTO();
				statusDTO.setConfig(false);
				statusDTO.setUltimoNsr(1000);
				StringEntity entity = new StringEntity(mapper.writeValueAsString(statusDTO));
				postRequest.setEntity(entity);

				HttpResponse response = httpClient.execute(postRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new ServiceException(response.getEntity().toString());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

				String output;
				while ((output = br.readLine()) != null) {
					LOGGER.info("Rep simulador dados recebido : " + output);
				}

				httpClient.getConnectionManager().shutdown();

				Thread.sleep(SimuladorRestController.intevalo);

			} catch (ClientProtocolException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
