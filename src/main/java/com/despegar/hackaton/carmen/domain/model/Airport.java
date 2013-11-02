package com.despegar.hackaton.carmen.domain.model;


import java.util.Map;

public class Airport {
    private Long oid;
    private String iataCode;
    private Map<String, String> localeAirportNameMap;

    public Airport() {
    }

    public Airport(Long oid, String iataCode, Map<String, String> description) {
        this.oid = oid;
        this.iataCode = iataCode;
        this.localeAirportNameMap = description;
    }

    public String getIataCode() {
        return this.iataCode;
    }

    public Map<String, String> getLocaleAirportNameMap() {
        return this.localeAirportNameMap;
    }



    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public void setLocaleAirportNameMap(Map<String, String> localeAirportNameMap) {
        this.localeAirportNameMap = localeAirportNameMap;
    }



    public Long getOid() {
        return this.oid;
    }



    public void setOid(Long oid) {
        this.oid = oid;
    }
}
