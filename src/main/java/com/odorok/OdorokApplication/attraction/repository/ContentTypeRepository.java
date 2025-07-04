package com.odorok.OdorokApplication.attraction.repository;

import com.odorok.OdorokApplication.draftDomain.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType, Integer> {

}
