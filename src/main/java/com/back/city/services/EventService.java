package com.back.city.services;

import com.back.city.dto.event.EventRequest;
import com.back.city.entity.EventEntity;
import com.back.city.repository.EventRepository;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final MinIoService minIOService;
    //TODO add mapper
    public EventEntity saveEvent(EventRequest event, List<MultipartFile> files) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        EventEntity newEvent = EventEntity.builder()
                .id(event.getId())
                .coordinates(event.getCoordinates())
                .description(event.getDescription())
                .userId(event.getUserId())
                .timestomp(new Date())
                .filesUrl(minIOService.uploadFile(files))
                .build();
        return eventRepository.save(newEvent);
    }

    public List<EventEntity> findEventsByUserId(Long userId){
        return eventRepository.findAllEventsByUserId(userId);
    }

    public List<EventEntity> findAllEvents(){
        return eventRepository.findAll();
    }

//    public void setLikes(Long eventId, boolean set){
//        EventEntity event = eventRepository.getById(eventId);
//
//    }
}
