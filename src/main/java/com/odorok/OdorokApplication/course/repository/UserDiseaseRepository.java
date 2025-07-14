package com.odorok.OdorokApplication.course.repository;

import com.odorok.OdorokApplication.domain.UserDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDiseaseRepository extends JpaRepository<UserDisease, Long> {
    List<UserDisease> findByUserId(Long userId);
}
