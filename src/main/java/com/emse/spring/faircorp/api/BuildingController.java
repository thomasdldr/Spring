package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController // (1)
@RequestMapping("/api/buildings") // (2)
@Transactional // (3)
public class BuildingController {

    private final BuildingDao buildingDao;
    private final RoomDao roomDao;
    private final HeaterDao heaterDao;
    private final WindowDao windowDao;


    public BuildingController(BuildingDao buildingDao, RoomDao roomDao, HeaterDao heaterDao, WindowDao windowDao) { // (4)
        this.buildingDao = buildingDao;
        this.roomDao = roomDao ;
        this.heaterDao = heaterDao;
        this.windowDao = windowDao;
    }

    @PostMapping //
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            building = buildingDao.save(new Building());
        }
        else {
            building = buildingDao.getOne(dto.getId());

        }
        return new BuildingDto(building);
    }

    @GetMapping //
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null); // (7)
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        List<RoomDto> dtos = roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
        for( RoomDto dto : dtos){
            if (dto.getBuildingId() == id) {
                heaterDao.deleteHeaters(dto.getId());
                windowDao.deleteWindows(dto.getId());
                roomDao.deleteById(dto.getId());
            }
         }
        buildingDao.deleteById(id);
    }
}
