package com.example.albartros.controller;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.DestinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("destination")
public class DestinationController {
    private final DestinationService destinationService;

    @PostMapping
    @Operation(summary = "Create Destination", description = "Create Destination ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Destination created successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            )
    })
    public HttpApiResponse<DestinationDto> createDestination(@RequestBody @Valid DestinationDto dto) {
        return this.destinationService.createDestination(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Destination", description = "Get Destination By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            )
    })
    public HttpApiResponse<DestinationDto> getDestinationById(@PathVariable Long id) {
        return this.destinationService.getDestinationById(id);
    }

    @Operation(summary = "Get Destinations", description = "Get DestinationList ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "DestinationList retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found Destinations"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            )
    })
    @GetMapping("/get-all")
    public HttpApiResponse<List<DestinationDto>> getAllDestinations() {
        return this.destinationService.getAllDestinations();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Destination", description = "Update Destination ById")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination Updated successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found with given id"
            )
    })
    public HttpApiResponse<DestinationDto> updateDestination(@PathVariable Long id, @RequestBody DestinationDto dto) {
        return this.destinationService.updateDestination(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Destination", description = "Delete Destination ById")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Authentication failed"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found with given id"
            )
    })
    public HttpApiResponse<String> deleteDestinationById(@PathVariable Long id) {
        return this.destinationService.deleteDestinationById(id);
    }
}
