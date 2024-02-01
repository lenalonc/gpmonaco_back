package com.gpmonaco.service;

import com.gpmonaco.dto.ZoneDTO;
import com.gpmonaco.entities.Zone;
import com.gpmonaco.repository.ZonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZonaServiceImpl implements ZonaService{

    ModelMapper mapper;
    ZonaRepository zonaRepository;

    public ZonaServiceImpl(ModelMapper mapper, ZonaRepository zonaRepository) {
        this.mapper = mapper;
        this.zonaRepository = zonaRepository;
    }

    @Override
    public List<ZoneDTO> getAllZones() {
        List<Zone>zone =  zonaRepository.findAll();
        return zone.stream().map(zona -> mapper.map(zona, ZoneDTO.class)).toList();
    }
}
