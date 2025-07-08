package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory,Long> {
}
