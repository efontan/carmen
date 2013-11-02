package com.despegar.hackaton.carmen.domain.model;


import java.util.List;
import java.util.Map;

import com.despegar.hackaton.carmen.domain.model.game.City;

public class GeoData
    implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Long> airportCodeCityOidMap;

    private Map<String, Airport> airportDescriptionMap;

    private Map<Long, City> cityOidCityMap;

    private Map<String, City> cityCodeCityMap;

    private Map<String, List<City>> iataCountryCitiesMap;

    private Map<String, String> cityCodeToAdminDivitionMap;

    public Map<String, Long> getAirportCodeCityOidMap() {
        return this.airportCodeCityOidMap;
    }

    public void setAirportCodeCityOidMap(Map<String, Long> airportCodeCityOidMap) {
        this.airportCodeCityOidMap = airportCodeCityOidMap;
    }

    public Map<Long, City> getCityOidCityMap() {
        return this.cityOidCityMap;
    }

    public void setCityOidCityMap(Map<Long, City> cityOidCityMap) {
        this.cityOidCityMap = cityOidCityMap;
    }

    public Map<String, City> getCityCodeCityMap() {
        return this.cityCodeCityMap;
    }

    public void setCityCodeCityMap(Map<String, City> cityCodeCityMap) {
        this.cityCodeCityMap = cityCodeCityMap;
    }

    public Map<String, List<City>> getIataCountryCitiesMap() {
        return this.iataCountryCitiesMap;
    }

    public void setIataCountryCitiesMap(Map<String, List<City>> iataCountryCitiesMap) {
        this.iataCountryCitiesMap = iataCountryCitiesMap;
    }

    public Map<String, String> getCityCodeToAdminDivitionMap() {
        return this.cityCodeToAdminDivitionMap;
    }

    public void setCityCodeToAdminDivitionMap(Map<String, String> cityCodeToAdminDivitionMap) {
        this.cityCodeToAdminDivitionMap = cityCodeToAdminDivitionMap;
    }

    public Map<String, Airport> getAirportDescriptionMap() {
        return this.airportDescriptionMap;
    }

    public void setAirportDescriptionMap(Map<String, Airport> airportDescriptionMap) {
        this.airportDescriptionMap = airportDescriptionMap;
    }

}
