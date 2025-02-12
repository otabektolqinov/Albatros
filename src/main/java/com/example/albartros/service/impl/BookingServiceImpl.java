package com.example.albartros.service.impl;

import com.example.albartros.dto.BookingDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.enums.BookingStatus;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.*;
import com.example.albartros.repository.*;
import com.example.albartros.service.BookingService;
import com.example.albartros.service.mapper.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final HotelRepository hotelRepository;
    private final DestinationRepository destinationRepository;
    private final AgencyRepository agencyRepository;
    private final BookingMapper bookingMapper;


    @Override
    @Transactional
    public HttpApiResponse<BookingDto> createBookingByUser(Long userId, BookingDto dto) {
        User user = userRepository.findByIdAndDeletedAtIsNull(userId)
                .orElseThrow(() -> new ContentNotFoundException("User with id " + userId + " does not exist"));

        Tours tour = tourRepository.findByIdAndDeletedAtIsNull(dto.getTourId())
                .orElseThrow(() -> new ContentNotFoundException("Tour with id " + dto.getTourId() + " does not exist"));

        Hotel hotel = hotelRepository.findByIdAndDeletedAtIsNull(dto.getHotelId())
                .orElseThrow(() -> new ContentNotFoundException("Hotel with id " + dto.getHotelId() + " does not exist"));

        Destination destination = destinationRepository.findByIdAndDeletedAtIsNull(dto.getDestinationId())
                .orElseThrow(() -> new ContentNotFoundException("Destination with id " + dto.getDestinationId() + " does not exist"));

        Agency agency = agencyRepository.findByIdAndDeletedAtIsNull(dto.getAgencyId())
                .orElseThrow(() -> new ContentNotFoundException("Agency with id " + dto.getAgencyId() + " does not exist"));

        Bookings entity = bookingMapper.toEntity(dto);
        entity.setUser(user);
        entity.setTours(tour);
        entity.setHotel(hotel);
        entity.setDestination(destination);
        entity.setAgency(agency);
        entity.setBookingDate(LocalDateTime.now());
        entity.setStatus(BookingStatus.PENDING);

        bookingRepository.save(entity);

        return HttpApiResponse.<BookingDto>builder()
                .status(HttpStatus.CREATED)
                .message("Booking created successfully")
                .content(bookingMapper.toDto(entity))
                .build();
    }


    @Override
    @Transactional
    public HttpApiResponse<BookingDto> createBooking(BookingDto dto) {
        User user = userRepository.findByIdAndDeletedAtIsNull(dto.getUserId())
                .orElseThrow(() -> new ContentNotFoundException("User with id " + dto.getUserId() + " does not exist"));

        Tours tour = tourRepository.findByIdAndDeletedAtIsNull(dto.getTourId())
                .orElseThrow(() -> new ContentNotFoundException("Tour with id " + dto.getTourId() + " does not exist"));

        Hotel hotel = hotelRepository.findByIdAndDeletedAtIsNull(dto.getHotelId())
                .orElseThrow(() -> new ContentNotFoundException("Hotel with id " + dto.getHotelId() + " does not exist"));

        Destination destination = destinationRepository.findByIdAndDeletedAtIsNull(dto.getDestinationId())
                .orElseThrow(() -> new ContentNotFoundException("Destination with id " + dto.getDestinationId() + " does not exist"));

        Agency agency = agencyRepository.findByIdAndDeletedAtIsNull(dto.getAgencyId())
                .orElseThrow(() -> new ContentNotFoundException("Agency with id " + dto.getAgencyId() + " does not exist"));


        Bookings entity = bookingMapper.toEntity(dto);
        entity.setUser(user);
        entity.setTours(tour);
        entity.setHotel(hotel);
        entity.setDestination(destination);
        entity.setAgency(agency);
        entity.setBookingDate(LocalDateTime.now());
        entity.setStatus(BookingStatus.PENDING);

        bookingRepository.save(entity);

        return HttpApiResponse.<BookingDto>builder()
                .status(HttpStatus.CREATED)
                .message("Booking created successfully")
                .content(this.bookingMapper.toDto(entity))
                .build();
    }


    @Override
    public HttpApiResponse<BookingDto> getBookingById(Long id) {
        try {
            if (bookingRepository.existsByIdAndDeletedAtIsNull(id)) {
                return HttpApiResponse.<BookingDto>builder()
                        .status(HttpStatus.OK)
                        .message("Booking get successfully")
                        .content(this.bookingMapper.toDto(this.bookingRepository.findByIdAndDeletedAtIsNull(id).orElse(null)))
                        .build();
            }
            throw new ContentNotFoundException("Booking with id " + id + " does not exist");
        } catch (Exception e) {
            throw new DatabaseException("Unable to get booking due to database error");
        }
    }

    @Override
    public HttpApiResponse<List<BookingDto>> getAllBookings() {
        try {
            if (this.bookingRepository.findAllByDeletedAtIsNull().isEmpty()) {
                throw new ContentNotFoundException("Booking list is empty");
            }
            List<BookingDto> bookingDtoList = this.bookingMapper.toDto(this.bookingRepository.findAllByDeletedAtIsNull());
            return HttpApiResponse.<List<BookingDto>>builder()
                    .status(HttpStatus.OK)
                    .message("Ok")
                    .content(bookingDtoList)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to get booking due to database error");
        }
    }

    @Override
    public HttpApiResponse<BookingDto> updateBooking(Long id, BookingDto dto) {
        try {
            Optional<Bookings> optional = this.bookingRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                throw new ContentNotFoundException("Booking with id " + id + " does not exist");
            }
            Bookings updateBooking = bookingMapper.updateBooking(optional.get(), dto);

            this.bookingRepository.save(updateBooking);

            return HttpApiResponse.<BookingDto>builder()
                    .status(HttpStatus.OK)
                    .message("Booking updated successfully")
                    .content(this.bookingMapper.toDto(updateBooking))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to update booking due to database error");
        }

    }

    @Override
    @Transactional
    public HttpApiResponse<String> deleteBookingById(Long id) {
        Bookings bookings = bookingRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ContentNotFoundException("Booking with id " + id + " does not exist"));

        bookings.setDeletedAt(LocalDateTime.now());
        bookingRepository.save(bookings);

        return HttpApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Booking deleted successfully")
                .build();
    }

}
