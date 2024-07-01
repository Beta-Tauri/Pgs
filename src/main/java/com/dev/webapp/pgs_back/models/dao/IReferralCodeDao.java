package com.dev.webapp.pgs_back.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.webapp.pgs_back.models.entity.ReferralCode;

@Repository
public interface IReferralCodeDao extends JpaRepository<ReferralCode, Long> {
    ReferralCode findByCode(String code);
    List<ReferralCode> findAll();
    ReferralCode findFirstByUsedFalse();
}
