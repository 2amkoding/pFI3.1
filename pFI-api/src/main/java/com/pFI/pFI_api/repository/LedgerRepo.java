package com.pFI.pFI_api.repository;

import com.pFI.pFI_api.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface LedgerRepo extends JpaRepository<Ledger, Long> {

    List<Ledger> findByUserId(Long userId);
    List<Ledger> findByUserIdAndCategory(Long userId, String category);
    List<Ledger> findByUserIdAndIsEssential(Long userId, Boolean isEssential);
    List<Ledger> findByUserIdAndMonth(Long userId, YaerMonth month);
    List<Ledger> findByUserIdAndMonthBetween(Long userId, YearMonth startDate, YearMonth endDate);




}
