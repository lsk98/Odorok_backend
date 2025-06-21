package com.odorok.OdorokApplication.infrastructures.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Sigungu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SigunguRepository extends JpaRepository<Sigungu, Integer> {

    public Optional<Sigungu> findByNameLikeAndSidoCode(String exp, Integer sidoCode);
}
