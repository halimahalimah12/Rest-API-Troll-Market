package com.indocyber.RestAPITrollMarket.controllers;

import com.indocyber.RestAPITrollMarket.dtos.Shipment.ShipmentResponseItemDto;
import com.indocyber.RestAPITrollMarket.dtos.profile.ProfileRequestDto;
import com.indocyber.RestAPITrollMarket.dtos.profile.ProfileResponseDto;
import com.indocyber.RestAPITrollMarket.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PutMapping("{id}")
    public ResponseEntity<ProfileResponseDto> addBalance(@RequestBody ProfileRequestDto dto, @PathVariable Integer id) {
        return ResponseEntity.ok(profileService.addBalace(dto));
    }
}
