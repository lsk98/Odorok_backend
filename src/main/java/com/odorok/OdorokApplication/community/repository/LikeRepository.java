package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
