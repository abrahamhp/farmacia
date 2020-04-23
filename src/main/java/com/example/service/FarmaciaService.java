package com.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.domain.Farmacia;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/*  Clase servicio que recibe el filtro y consume la API de Farmacias en turno de la región metropolitana */
@Service("FarmaciaService")
public class FarmaciaService {
	
	//URL de la API de Farmacias en turno de la region metropolitana
	private static final String targetURL = "https://farmanet.minsal.cl/maps/index.php/ws/getLocalesRegion?id_region=7";
	
	static String json = "...";
	
	//metodo que consume la API de Farmacias de Turno
	public List<Farmacia> callFarmaciaApi(String comunaNombre, String localNombre) {
		   
			List<Farmacia> listaFarmacias = new ArrayList<Farmacia>(); 
	          try {
	 
	            URL restServiceURL = new URL(targetURL);
	 
	            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
	            httpConnection.setRequestMethod("GET");
	            httpConnection.setRequestProperty("Accept", "application/json");
	 
	            if (httpConnection.getResponseCode() != 200) {
	                throw new RuntimeException("HTTP GET Request Failed with Error code : "
	                        + httpConnection.getResponseCode());
	            }
	 
	            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
	                (httpConnection.getInputStream())));
	 
	            String output;	           
	          
	            while ((output = responseBuffer.readLine()) != null) {
	                
	         
	            	  json=output;
	            	  json="{"+'"'+"posts"+'"'+":"+json+"}"; //asigno nombre al JSON como "posts" para su lectura y parseo
	            	  
	                JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
	                  
	            	JsonArray arr = jsonObject.getAsJsonArray("posts");	 // Lee los objetos del array del JSON y los asigna
	            	
	                for (int i = 0; i < arr.size(); i++) {
	                	
	                	//Extrae los parametros del JSON para realizar el filtro
	                    String local_nombre = arr.get(i).getAsJsonObject().get("local_nombre").getAsString();
	                    String comuna_nombre = arr.get(i).getAsJsonObject().get("comuna_nombre").getAsString();
	                  
	                    if ( (comunaNombre.equals(comuna_nombre)  ) && localNombre.equals(local_nombre))
	                    {
	                    	 Farmacia farm = new Farmacia();
	                    	 
	                    	 farm.setComuna_nombre(comuna_nombre);
	                    	 farm.setLocal_nombre(local_nombre);
	                    	 farm.setLocal_direccion(arr.get(i).getAsJsonObject().get("local_direccion").getAsString());
	                         farm.setLocal_telefono(arr.get(i).getAsJsonObject().get("local_telefono").getAsString());
	                         farm.setLocal_lat(arr.get(i).getAsJsonObject().get("local_lat").getAsString());
	                         farm.setLocal_lng(arr.get(i).getAsJsonObject().get("local_lng").getAsString());
	                         	                         
	                         listaFarmacias.add(farm);
	                    	
	                    }
	                    
	                }
	                
	               
	            }
	           
	 
	           httpConnection.disconnect();	           
	 
	          } catch (MalformedURLException e) {
	 
	            e.printStackTrace();
	 
	          } catch (IOException e) {
	 
	            e.printStackTrace();
	 
	          }
			return listaFarmacias;	
		
	}

}
