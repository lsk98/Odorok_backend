package com.odorok.OdorokApplication.region.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Sigungu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SigunguRepository extends JpaRepository<Sigungu, Integer> {
    List<Sigungu> findBySidoCode(Integer sidoCode);
    public Optional<Sigungu> findByNameLikeAndSidoCode(String exp, Integer sidoCode);
}
