package ru.yumeno.nir.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yumeno.nir.dto.DistrictDTO;
import ru.yumeno.nir.entity.District;
import ru.yumeno.nir.service.DistrictService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/districts")
@Api
@Slf4j
public class DistrictController {
    private final DistrictService districtService;

    @Autowired
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping(value = "")
    @ApiOperation("Получение всех районов")
    public List<DistrictDTO> getAllDistricts() {
        log.info("Try to get all districts");
        return districtService.getAllDistricts().stream().map(this::toDistrictDTO).toList();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Получение района по его id")
    public DistrictDTO getDistrictById(@PathVariable int id) {
        log.info("Try to get district by id = " + id);
        return toDistrictDTO(districtService.getDistrictById(id));
    }

    @PostMapping(value = "")
    @ApiOperation("Добавлние района")
    public DistrictDTO addDistrict(@Valid @RequestBody DistrictDTO districtDTO) {
        log.info("Try to add district: " + districtDTO.toString());
        return toDistrictDTO(districtService.addDistrict(toDistrict(districtDTO)));
    }

    @PutMapping(value = "")
    @ApiOperation("Обновление района")
    public DistrictDTO updateDistrict(@Valid @RequestBody DistrictDTO districtDTO) {
        log.info("Try to update district: " + districtDTO.toString());
        return toDistrictDTO(districtService.updateDistrict(toDistrict(districtDTO)));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Удаление района")
    public void deleteDistrict(@PathVariable int id) {
        log.info("Try to delete district by id = " + id);
        districtService.deleteDistrict(id);
    }

    private District toDistrict(DistrictDTO districtDTO) {
        return District.builder()
                .id(districtDTO.getId())
                .name(districtDTO.getName())
                .build();
    }

    private DistrictDTO toDistrictDTO(District district) {
        return DistrictDTO.builder()
                .id(district.getId())
                .name(district.getName())
                .build();
    }
}
