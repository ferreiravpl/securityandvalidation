package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO insert(EventDTO dto) {
        City cityEntity = cityRepository.getReferenceById(dto.getCityId());
        Event entity = new Event();
        entity.setUrl(dto.getUrl());
        entity.setDate(dto.getDate());
        entity.setName(dto.getName());
        entity.setCity(cityEntity);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> result = repository.findAll(pageable);
        return result.map(EventDTO::new);
    }
}
