package com.gpmonaco.service;

import com.gpmonaco.dto.DailyPlanDTO;
import com.gpmonaco.entities.DailyPlan;
import java.util.List;
import java.util.Map;

public interface DailyPlanService {
    public DailyPlanDTO createDailyPlan(DailyPlan dailyPlan);

    public List<DailyPlanDTO> getAllDailyPlans();

    public Map<Long, Double> getMinPrice();

}
