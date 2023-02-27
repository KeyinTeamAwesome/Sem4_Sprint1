package com.keyin.airport;

public class AirportOTA {

    private String airportName;
    private String airportCode;

    private long cityId;

    private long aircraftId;

    public String getName() {
        return airportName;
    }

    public void setName(String airportName) {
        this.airportName = airportName;
    }

    public String getCode() {
        return airportCode;
    }

    public void setCode(String code) {
        this.airportCode = airportCode;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(long aircraftId) {
        this.aircraftId = aircraftId;
    }
}
