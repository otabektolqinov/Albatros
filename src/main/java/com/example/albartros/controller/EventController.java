package com.example.albartros.controller;

import com.example.albartros.dto.EventDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public HttpApiResponse<EventDto> createEvent(@RequestBody EventDto dto){
        return eventService.createEvent(dto);
    }

    @GetMapping("{id}")
    public HttpApiResponse<EventDto> getEventById(@PathVariable("id") Long id){
        return eventService.getEventById(id);
    }

    @PostMapping("/{id}")
    public HttpApiResponse<EventDto> updateEventById(@PathVariable("id") Long id,
                                                     @RequestBody EventDto dto){
        return eventService.updateEventById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<EventDto> deleteEventById(@PathVariable("id") Long id){
        return eventService.deleteEventById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<EventDto>> getAllEvents(){
        return eventService.getAllEvents();
    }

}
