package com.odorok.OdorokApplication.infrastructures.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Gil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GilRepository extends JpaRepository<Gil, Long> {
}
