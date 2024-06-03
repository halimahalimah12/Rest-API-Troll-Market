package com.indocyber.RestAPITrollMarket.controllers;

import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentDropdownDto;
import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentRequestItemDto;
import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentResponseItemDto;
import com.indocyber.RestAPITrollMarket.services.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shipment")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<ShipmentDropdownDto>> getAll() {
        return ResponseEntity.ok(shipmentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ShipmentResponseItemDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(shipmentService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<ShipmentResponseItemDto> insert(@Valid @RequestBody ShipmentRequestItemDto dto) {
        return ResponseEntity.ok(shipmentService.save(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ShipmentResponseItemDto> update (@Valid @RequestBody ShipmentRequestItemDto dto,@PathVariable Integer id) {
        return ResponseEntity.ok(shipmentService.update(dto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ShipmentResponseItemDto> delete (@PathVariable Integer id) {
        return ResponseEntity.ok(shipmentService.delete(id));
    }

    @PutMapping("stopService/{id}")
    public ResponseEntity<ShipmentResponseItemDto> updateIsService (@PathVariable Integer id) {
        return ResponseEntity.ok(shipmentService.stopService(id));
    }


}
