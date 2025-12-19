package com.back.city.controller;

import com.back.city.dto.event.EventRequest;
import com.back.city.dto.event.EventResponse;
import com.back.city.entity.EventEntity;
import com.back.city.services.EventService;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @GetMapping("/findAll")
    public List<EventResponse> getAllEvents(){

        return eventService.findAllEvents();
    }

    @PostMapping("/saveEvent")
    public EventResponse saveEvent (@RequestParam("file") List<MultipartFile> file, @RequestPart("event") EventRequest event) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println(file);
        return eventService.saveEvent(event, file);
    }

    @GetMapping("/{id}")
    public List<EventEntity> getEventsByUserId(@PathVariable Long id){
        return eventService.findEventsByUserId(id);
    }

//    @PostMapping("{id}/likes")
//    public ResponseEntity setLikes(@PathVariable("id") Long eventId, @RequestBody boolean set){
//
//        return ResponseEntity.ok("likeSet");
//    }
}
