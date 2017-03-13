package com.api.simulador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.rep.service.ServiceException;
import com.api.simulador.entity.Iniciar;
import com.api.simulador.entity.RepSimulador;
//import com.api.simulador.service.ClienteRep;
import com.api.simulador.service.RepSimuladorService;

@RestController
@RequestMapping(value = "simulador")
public class SimuladorRestController {

	@Autowired
	private RepSimuladorService repSimuladorService;

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<RepSimulador> adicionarRepSimulador(@RequestBody RepSimulador repSimulador)
			throws ServiceException {
		return new ResponseEntity<>(this.repSimuladorService.salvar(repSimulador), HttpStatus.CREATED);
	}

	Runnable cliente = null;
	Thread t;
	
	public static boolean init = false;
	public static int intevalo = 1000;

	@RequestMapping(value = "/iniciar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Iniciar> adicionarRepSimulador(@RequestBody Iniciar iniciar)
			throws ServiceException, InterruptedException {

		this.init = iniciar.getIniciar();
		this.intevalo = iniciar.getIntervalo();
		
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					ClienteRep.execut();
//				} catch (ServiceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}); 
		
//		thread.start();
		return new ResponseEntity<>(iniciar, HttpStatus.CREATED);
	}

}
