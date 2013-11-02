package com.despegar.hackaton.carmen.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;

@Configuration
public class CitiesMapConfig {

	@Bean(name = "citiesMap")
	public Map<String, String> getBrandMap() {
		Map<String, String> cities = Maps.newHashMap();

		cities.put("BUE", "Buenos Aires");
		cities.put("COR", "Cóordoba");
		cities.put("CNQ", "Corrientes");
		cities.put("FMA", "Formosa");
		cities.put("IRJ", "La Rioja");
		cities.put("MDZ", "Mendoza");
		cities.put("PSS", "Posadas");
		cities.put("RGL", "Río Gallego");
		cities.put("REL", "Trelew");
		cities.put("RES", "Resistencia");
		cities.put("SFN", "Santa Fe");
		cities.put("SLA", "Salta");
		cities.put("TUC", "Tucumán");
		cities.put("SDE", "Santiago del Estero");
		cities.put("JUJ", "San Salvador de Jujuy");
		cities.put("LUQ", "San Luis");
		cities.put("RSA", "Santa Rosa");
		cities.put("CTC", "Catamarca");
		cities.put("VDM", "Viedma");
		cities.put("USH", "Ushuaia");
		cities.put("UAQ", "San Juan");
		cities.put("NQN", "Neuquén");

		return cities;
	}
}
