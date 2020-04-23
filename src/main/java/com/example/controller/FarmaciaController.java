package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.FarmaciaApplication;
import com.example.domain.Farmacia;
import com.example.service.FarmaciaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/farmacia")
public class FarmaciaController {
	
	@Autowired
    private FarmaciaService farmaciaService;
	private static final Logger log = LoggerFactory.getLogger(FarmaciaApplication.class);

	 @ApiOperation(value = "Lista de farmacias de turno") 
	 @ApiResponses({ @ApiResponse(code = 204, message = "Farmacia no encontrada", response = HttpStatus.class),
     @ApiResponse(code = 200, message = "Farmacia de turno", response = Farmacia.class) })
	 @RequestMapping(value = "/listarFarmacias/{comuna}/{nombreLocal}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	 public List<Farmacia> listarFarmacias(@PathVariable("comuna") String comuna,
			 						 @PathVariable("nombreLocal") String nombreLocal ) {

		 try {
			 
			 List<Farmacia> farmacias = farmaciaService.callFarmaciaApi(comuna, nombreLocal);
			 if(farmacias != null){
				 return  farmacias;
			 }

		 } catch (Exception ex) {
			 log.error("ws-buscarFarmaciasPorComunaLocal", ex);
		 }
		 return null;
	 }
	 
	 
  
}
