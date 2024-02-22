package com.gpmonaco.service;

import com.gpmonaco.dto.DayDTO;
import com.gpmonaco.entities.Day;
import com.gpmonaco.repository.DayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private ModelMapper mapper;

    public DayServiceImpl(DayRepository danRepository) {
        this.dayRepository = danRepository ;
    }

    @Override
    public List<DayDTO> getAllDays() {
        List<Day> days = dayRepository.findAll();
        return days.stream().map(day -> mapper.map(day, DayDTO.class)).toList();
    }
}
