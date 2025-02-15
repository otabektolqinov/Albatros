package com.example.albartros.controller;

import com.example.albartros.dto.EventDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events/")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Create an Event", description = "Add a new Event")
    @PostMapping
    public HttpApiResponse<EventDto> createEvent(@RequestBody EventDto dto){
        return eventService.createEvent(dto);
    }

    @Operation(summary = "Get an Event By ID", description = "Fetch an Event using its ID")
    @GetMapping("{id}")
    public HttpApiResponse<EventDto> getEventById(@PathVariable("id") Long id){
        return eventService.getEventById(id);
    }

    @Operation(summary = "Update an Event by ID",
            description = "Update an Event using its and giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<EventDto> updateEventById(@PathVariable("id") Long id,
                                                     @RequestBody EventDto dto){
        return eventService.updateEventById(id, dto);
    }

    @Operation(summary = "Delete an Event by ID",
            description = "Update an Event using its ID and giving the field and value")
    @DeleteMapping("/{id}")
    public HttpApiResponse<EventDto> deleteEventById(@PathVariable("id") Long id){
        return eventService.deleteEventById(id);
    }

    @Operation(summary = "Get all Events", description = "Fetch all events")
    @GetMapping("/get-all")
    public HttpApiResponse<List<EventDto>> getAllEvents(){
        return eventService.getAllEvents();
    }

}
