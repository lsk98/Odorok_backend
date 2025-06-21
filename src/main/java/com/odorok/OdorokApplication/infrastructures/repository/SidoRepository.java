package com.odorok.OdorokApplication.infrastructures.repository;

import com.odorok.OdorokApplication.infrastructures.domain.Sido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Integer> {

    public Optional<Sido> findByNameLike(String exp);

}
