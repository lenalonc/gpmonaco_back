package com.gpmonaco.service;

import com.gpmonaco.dto.DailyPlanDTO;
import com.gpmonaco.dto.ZoneDTO;
import com.gpmonaco.entities.DailyPlan;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DailyPlanServiceImpl implements DailyPlanService {

    com.gpmonaco.repository.DailyPlanRepository dailyPlanRepository;
    ModelMapper mapper;

    @Override
    public DailyPlanDTO createDailyPlan(DailyPlan dailyPlan) {
        dailyPlan.setCapacity(dailyPlan.getZone().getFeatures().getCapacity());
        return mapper.map(dailyPlanRepository.save(dailyPlan), DailyPlanDTO.class);
    }

    @Override
    public List<DailyPlanDTO> getAllDailyPlans() {
        List<DailyPlan> plans = dailyPlanRepository.findAll();
        return plans.stream().map(plan -> mapper.map(plan, DailyPlanDTO.class)).toList();
    }


}
