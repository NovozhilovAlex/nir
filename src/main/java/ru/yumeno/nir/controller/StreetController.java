package ru.yumeno.nir.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yumeno.nir.dto.StreetDTO;
import ru.yumeno.nir.entity.Street;
import ru.yumeno.nir.service.StreetService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/streets")
@Api
@Slf4j
public class StreetController {
    private final StreetService streetService;

    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping(value = "")
    @ApiOperation("Получение всех улиц")
    public List<StreetDTO> getAllStreets() {
        log.info("Try to get all streets");
        return streetService.getAllStreets().stream().map(this::toStreetDTO).toList();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получение улицы по ее id")
    public StreetDTO getStreetById(@PathVariable int id) {
        log.info("Try to get street by id = " + id);
        return toStreetDTO(streetService.getStreetById(id));
    }

    @PostMapping(value = "")
    @ApiOperation("Добавлние улицы")
    public StreetDTO addStreet(@Valid @RequestBody StreetDTO streetDTO) {
        log.info("Try to add street: " + streetDTO.toString());
        return toStreetDTO(streetService.addStreet(toStreet(streetDTO)));
    }

    @PutMapping(value = "")
    @ApiOperation("Обновление улицы")
    public StreetDTO updateStreet(@Valid @RequestBody StreetDTO streetDTO) {
        log.info("Try to update street: " + streetDTO.toString());
        return toStreetDTO(streetService.updateStreet(toStreet(streetDTO)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Удаление улицы")
    public void deleteStreet(@PathVariable int id) {
        log.info("Try to delete street by id = " + id);
        streetService.deleteStreet(id);
    }

    private Street toStreet(StreetDTO streetDTO) {
        return Street.builder()
                .id(streetDTO.getId())
                .name(streetDTO.getName())
                .build();
    }

    private StreetDTO toStreetDTO(Street street) {
        return StreetDTO.builder()
                .id(street.getId())
                .name(street.getName())
                .build();
    }
}