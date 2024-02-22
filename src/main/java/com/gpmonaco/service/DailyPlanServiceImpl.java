package com.gpmonaco.service;

import com.gpmonaco.dto.DailyPlanDTO;
import com.gpmonaco.entities.DailyPlan;
import com.gpmonaco.entities.Day;
import com.gpmonaco.repository.DailyPlanRepository;
import com.gpmonaco.repository.DayRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DailyPlanServiceImpl implements DailyPlanService {

    @Autowired
    DailyPlanRepository dailyPlanRepository;
    @Autowired
    DayRepository dayRepository;
    @Autowired
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

    @Override
    public Map<Long, Double> getMinPrice() {
        List<DailyPlan> plans = dailyPlanRepository.findAll();
        return getMin(plans);
    }


    private Map<Long, Double> getMin(List<DailyPlan> plans){
        List<DailyPlan> plansMin = new ArrayList<>();
        Map<Long, Double> minPriceMap = new HashMap<>();
        List<Day> days = dayRepository.findAll();
        for (Day day : days) {
            minPriceMap.put(day.getId(), Double.MAX_VALUE);
        }


        for (DailyPlan plan:plans
             ) {
            Long dayId = plan.getDay().getId();
            Double price = plan.getZone().getFeatures().getPrice();

                if(price < minPriceMap.get(dayId)){
                    minPriceMap.put(dayId, price);
                }
        }

        return minPriceMap;
    }

}
