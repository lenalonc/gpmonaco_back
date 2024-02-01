package com.gpmonaco.service;

import com.gpmonaco.entities.Day;
import com.gpmonaco.repository.DayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    private DayRepository dayRepository;

    public DayServiceImpl(DayRepository danRepository) {
        this.dayRepository = danRepository ;
    }

    @Override
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }
}
