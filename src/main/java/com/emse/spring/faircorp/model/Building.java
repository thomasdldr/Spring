package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BUILDING")
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)
    private String name;

    @OneToMany(targetEntity=Room.class, mappedBy="building")
    private Set<Room> rooms;

    public Building() {
    }

    public Building(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
