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
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    private final RoomDao roomDao;
    private final BuildingDao buildingDao;
    private final HeaterDao heaterDao;
    private final WindowDao windowDao;


    public RoomController(RoomDao roomDao, BuildingDao buildingDao, HeaterDao heaterDao, WindowDao windowDao ) { // (4)
        this.roomDao = roomDao;
        this.buildingDao = buildingDao ;
        this.heaterDao = heaterDao ;
        this.windowDao = windowDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switchWindow")
    public RoomDto switchStatusWindow(@PathVariable Long id, Long windowId) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindows();
        for(Window window : windows){
            if (window.getId()==windowId){
                window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
            }
        }
        room.setWindows(windows);
        return new RoomDto(room);
    }

    @PutMapping(path = "/{id}/switchHeater")
    public RoomDto switchStatusHeater(@PathVariable Long id,  Long heaterId) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heaters = room.getHeaters();
        for(Heater heater : heaters){
            if (heater.getId()==heaterId){
                heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
            }
        }
        room.setHeaters(heaters);
        return new RoomDto(room);
    }



    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        Building building = buildingDao.getOne(dto.getBuildingId());
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room( dto.getName(),  dto.getCurrentTemperature(),  dto.getTargetTemperature(), dto.getFloor(), building ));
        }
        else {
            room = roomDao.getOne(dto.getId());  // (9)
            room.setCurrentTemperature(dto.getCurrentTemperature());
            room.setTargetTemperature(dto.getTargetTemperature());
            room.setFloor(dto.getFloor());
            room.setBuilding(building);

        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteHeaters(id);
        windowDao.deleteWindows(id);
        roomDao.deleteById(id);
    }
}