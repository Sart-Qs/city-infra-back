package com.back.city.services;

import com.back.city.dto.event.EventRequest;
import com.back.city.dto.event.EventResponse;
import com.back.city.dto.user.UserDTO;
import com.back.city.entity.EventEntity;
import com.back.city.mapper.EventMapper;
import com.back.city.mapper.UserMapper;
import com.back.city.repository.EventRepository;
import com.back.city.repository.UserRepository;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    //TODO поменять во всех сервисах где используется сторонний репозиторий на сервисы
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final MinIoService minIOService;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;


    //TODO поменять на responeEntity ok
    @Transactional
    public EventResponse saveEvent(EventRequest event, List<MultipartFile> files) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<String> fileUrl = minIOService.uploadFile(files);
        UserDTO user = userMapper.toUserDTO(userRepository.findUserById(event.getUserId()));
        EventEntity newEvent = eventMapper.toEventEntity(event, fileUrl);
        eventRepository.save(newEvent);
        return eventMapper.toEventResponse(newEvent, user);
    }

    @Transactional
    public List<EventEntity> findEventsByUserId(Long userId){
        return eventRepository.findAllEventsByUserId(userId);
    }

    @Transactional
    public List<EventResponse> findAllEvents(){
        List<EventEntity> eventEntities = eventRepository.findAll();
        return eventEntities.stream().map((e) ->{
            UserDTO user = userMapper.toUserDTO(userRepository.findUserById(e.getUserId()));
            return eventMapper.toEventResponse(e, user);
        }).toList();
    }

    public void setLikes(Long eventId, boolean set){

    }
}
