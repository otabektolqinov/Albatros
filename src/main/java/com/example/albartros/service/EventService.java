package com.example.albartros.service;

import com.example.albartros.dto.EventDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    HttpApiResponse<EventDto> createEvent(EventDto dto);
    HttpApiResponse<EventDto> getEventById(Long id);
    HttpApiResponse<EventDto> updateEventById(Long id, EventDto dto);
    HttpApiResponse<EventDto> deleteEventById(Long id);
    HttpApiResponse<List<EventDto>> getAllEvents();

}
