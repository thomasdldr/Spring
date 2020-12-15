package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BUILDING")
public class Building {



    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(targetEntity=Room.class, mappedBy="building")
    private Set<Room> rooms;

    public Building() {
    }

    public Building(Set<Room> rooms){
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
