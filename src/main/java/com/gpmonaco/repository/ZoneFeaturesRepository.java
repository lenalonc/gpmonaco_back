package com.gpmonaco.repository;

import com.gpmonaco.entities.ZoneFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneFeaturesRepository extends JpaRepository<ZoneFeatures, Long> {
}
