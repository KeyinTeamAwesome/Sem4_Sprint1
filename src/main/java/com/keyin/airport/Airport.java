package com.keyin.airport;


import com.keyin.city.City;

import javax.persistence.*;

@Entity
public class Airport {
    @Id
    @SequenceGenerator(name = "airport_sequence", sequenceName = "airport_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "airport_sequence")
    private long id;
    private String name;
    private String code;

    @ManyToOne
    private City city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirportName() {
        return name;
    }

    public void setAirportName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return code;
    }

    public void setAirportCode(String code) {
        this.code = code;
    }
}

