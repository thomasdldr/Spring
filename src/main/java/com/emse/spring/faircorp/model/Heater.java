package com.emse.spring.faircorp.model;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "HEATER")
public class Heater {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false, length=255)
    private String name;

    private Long power;

    @Column(nullable=false, length=255)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    @ManyToOne
    @NotNull
    private Room room;

    public Heater() {
    }

    public Heater(String name, HeaterStatus status, Long power, Room room) {
        this.heaterStatus = status;
        this.name = name;
        this.power = power;
        this.room = room;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }
}