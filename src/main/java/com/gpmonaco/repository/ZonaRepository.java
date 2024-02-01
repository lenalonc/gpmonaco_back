package com.gpmonaco.repository;

import com.gpmonaco.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zone, Long> {

}
