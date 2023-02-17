package com.keyin.city;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1, initialValue=10)
    @GeneratedValue(generator = "city_sequence")
    private long id;

    private String name;

    @OneToOne
    private TownHall townHall;

    @ManyToMany
    private List<Citizen> citizens;

    public City() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TownHall getTownHall() {
        return townHall;
    }

    public void setTownHall(TownHall townHall) {
        this.townHall = townHall;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }
}
