package com.odorok.OdorokApplication.region.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Integer> {

    Optional<Sido> findByNameLike(String exp);

}
