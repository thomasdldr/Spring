package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;

public class BuildingDto {
    private Long id;

    public BuildingDto() {
    }

    public BuildingDto(Building building) {
        this.id = building.getId();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}