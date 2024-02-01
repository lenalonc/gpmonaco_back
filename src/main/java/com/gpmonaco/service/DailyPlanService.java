package com.gpmonaco.service;

import com.gpmonaco.dto.DailyPlanDTO;
import com.gpmonaco.entities.DailyPlan;
import java.util.List;

public interface DailyPlanService {
    public DailyPlanDTO createDailyPlan(DailyPlan dailyPlan);

    public List<DailyPlanDTO> getAllDailyPlans();

}
