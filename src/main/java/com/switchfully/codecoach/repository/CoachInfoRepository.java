package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.user.CoachInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoachInfoRepository extends JpaRepository<CoachInfo, UUID> {
}
