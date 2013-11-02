package com.despegar.hackaton.carmen.domain.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class City
    implements Serializable {

    private static final long serialVersionUID = 221621173371639912L;

    private Long oid;
    private Long countryOID;
    private HashMap<String, String> name;
    private String iataCode;
    private Map<String, String> localeCountryNameMap;
    private String countryCode;
    private String administrativeDivisionCode;
    private String continentCode;
    private Double offset;

    public City() {
        super();
        this.name = new HashMap<String, String>();
    }

    public City(Long oid, Long countryOid, HashMap<String, String> name, String iataCode,
        Map<String, String> localeCountryNameMap) {
        this();
        this.oid = oid;
        this.countryOID = countryOid;
        this.name = name;
        this.iataCode = iataCode;
        this.localeCountryNameMap = localeCountryNameMap;
    }

    public Long getCountryOID() {
        return this.countryOID;
    }

    public void setCountryOID(Long countryOID) {
        this.countryOID = countryOID;
    }

    public HashMap<String, String> getName() {
        return this.name;
    }

    public void setName(HashMap<String, String> name) {
        this.name = name;
    }

    public String getIataCode() {
        return this.iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public Map<String, String> getLocaleCountryNameMap() {
        return this.localeCountryNameMap;
    }

    public void setLocaleCountryNameMap(Map<String, String> localeCountryNameMap) {
        this.localeCountryNameMap = localeCountryNameMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAdministrativeDivisionCode() {
        return this.administrativeDivisionCode;
    }

    public void setAdministrativeDivisionCode(String administrativeDivision) {
        this.administrativeDivisionCode = administrativeDivision;
    }

    public String getContinentCode() {
        return this.continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

}
