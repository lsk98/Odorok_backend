package com.odorok.OdorokApplication.community.repository;

import com.odorok.OdorokApplication.draftDomain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
