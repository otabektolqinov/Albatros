package com.example.albartros.service.impl;

import com.example.albartros.dto.EventDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Event;
import com.example.albartros.repository.EventRepository;
import com.example.albartros.service.EventService;
import com.example.albartros.service.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public HttpApiResponse<EventDto> createEvent(EventDto dto) {
        try {
            Event entity = eventMapper.toEntity(dto);
            Event saved = eventRepository.save(entity);

            return HttpApiResponse.<EventDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("ok")
                    .content(eventMapper.toDto(saved))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<EventDto> getEventById(Long id) {
        try {
            Optional<Event> optional = eventRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Event with %d id is not found", id));
            }

            Event event = optional.get();

            return HttpApiResponse.<EventDto>builder()
                    .content(eventMapper.toDto(event))
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<EventDto> updateEventById(Long id, EventDto dto) {
        try {
            Optional<Event> optional = eventRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Event with %d id is not found", id));
            }

            Event event = optional.get();
            Event updated = eventMapper.updateAllFields(event, dto);
            Event saved = eventRepository.save(updated);

            return HttpApiResponse.<EventDto>builder()
                    .status(HttpStatus.OK)
                    .message("Successfully updated")
                    .content(eventMapper.toDto(saved))
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<EventDto> deleteEventById(Long id) {
        try {
            Optional<Event> optional = eventRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Event with %d id is not found", id));
            }

            Event event = optional.get();
            event.setDeletedAt(LocalDateTime.now());
            eventRepository.save(event);

            return HttpApiResponse.<EventDto>builder()
                    .status(HttpStatus.OK)
                    .message("Successfully deleted")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<EventDto>> getAllEvents() {
        try {
            List<Event> events = eventRepository.findAllByDeletedAtIsNull();
            if (events.isEmpty()){
                throw new ContentNotFoundException("Event list is empty");
            }

            return HttpApiResponse.<List<EventDto>>builder()
                    .message("OK")
                    .content(eventMapper.toDtoList(events))
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
