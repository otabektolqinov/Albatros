package com.example.albartros.controller;

import com.example.albartros.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("hotel")
public class HotelController {

    private final HotelService hotelService;

}
