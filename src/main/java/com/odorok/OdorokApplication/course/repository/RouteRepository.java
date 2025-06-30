package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByIdx(String idx);
}
