package com.indocyber.RestAPITrollMarket.services;

import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentDropdownDto;
import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentRequestItemDto;
import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentResponseItemDto;
import com.indocyber.RestAPITrollMarket.dtos.excaption.DependencyExistsException;
import com.indocyber.RestAPITrollMarket.dtos.excaption.EntityNotFoundExcaption;
import com.indocyber.RestAPITrollMarket.models.Shipment;
import com.indocyber.RestAPITrollMarket.repositories.OrderRepository;
import com.indocyber.RestAPITrollMarket.repositories.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, OrderRepository orderRepository) {
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
    }

    public List<ShipmentDropdownDto> getAll(){
        List<Shipment> shipments = shipmentRepository.findAllIsService();
        List<ShipmentDropdownDto> shipmentResponseItemDtoList = shipments.stream()
                .map(shipment -> ShipmentDropdownDto.builder()
                        .id(shipment.getId())
                        .name(shipment.getName())
                        .build())
                .toList();
        return shipmentResponseItemDtoList;

    }


    private Shipment findById(Integer id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExcaption("Id not found"));
    }

    public ShipmentResponseItemDto getById(Integer id) {
        Shipment shipment = findById(id);
        return convertShipmentResponseItemDto(shipment);
    }

    private ShipmentResponseItemDto convertShipmentResponseItemDto(Shipment shipment) {
        return ShipmentResponseItemDto.builder()
                .id(shipment.getId())
                .name(shipment.getName())
                .price(shipment.getPrice())
                .isService(shipment.getIsService())
                .build();
    }

    public ShipmentResponseItemDto save(ShipmentRequestItemDto dto) {
        var shipment = Shipment.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .isService(dto.getIsService())
                .build();
        shipment = shipmentRepository.save(shipment);
        return convertShipmentResponseItemDto(shipment);
    }

    public ShipmentResponseItemDto update(ShipmentRequestItemDto dto) {
        Integer dependency = orderRepository.countShipment(dto.getId());

        if (dependency == 0) {
            var shipment = Shipment.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .price(dto.getPrice())
                    .isService(true)
                    .build();
            shipment = shipmentRepository.save(shipment);
            return convertShipmentResponseItemDto(shipment);
        } else {
            throw  new DependencyExistsException("Cannot update Shipment because ther are existing dependencies.");
        }

    }


    public ShipmentResponseItemDto delete(Integer id) {
        Shipment shipment = findById(id);
        shipmentRepository.deleteById(id);
        return convertShipmentResponseItemDto(shipment);
    }

    public ShipmentResponseItemDto stopService(Integer id) {
        Shipment shipment = findById(id);
        shipment.setIsService(false);
        shipment = shipmentRepository.save(shipment);
        return convertShipmentResponseItemDto(shipment);
    }
}
