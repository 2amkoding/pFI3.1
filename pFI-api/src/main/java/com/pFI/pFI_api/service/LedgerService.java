package com.pFI.pFI_api.service;

import com.pFI.pFI_api.dto.LedgerCreateDTO;
import com.pFI.pFI_api.dto.LedgerDTO;
import com.pFI.pFI_api.dto.LedgerUpdateDTO;
import com.pFI.pFI_api.entity.Category;
import com.pFI.pFI_api.entity.Ledger;
import com.pFI.pFI_api.mapper.LedgerMapper;
import com.pFI.pFI_api.repository.CategoryRepo;
import com.pFI.pFI_api.repository.LedgerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepo ledgerRepo;
    private final LedgerMapper ledgerMapper;
    private final CategoryRepo categoryRepo;

    public List<LedgerDTO> getAllLedgers() {
        return ledgerRepo.findAll().stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<LedgerDTO> getLedgerById(Long id) {
        return ledgerRepo.findById(id)
                .map(ledgerMapper::toDTO);
    }

    @Transactional
    public LedgerDTO createLedger(LedgerCreateDTO ledgerCreateDTO) {
        Ledger ledger = ledgerMapper.toEntity(ledgerCreateDTO);
        ledger = ledgerRepo.save(ledger);
        return ledgerMapper.toDTO(ledger);
    }
    @Transactional
    public Optional<LedgerDTO> updateLedger(Long id, LedgerUpdateDTO ledgerUpdateDTO) {
        return ledgerRepo.findById(id)
                .map(ledger ->{
                    ledgerMapper.updateEntityFromDTO(ledgerUpdateDTO, ledger);
                    return ledgerRepo.save(ledger);
                })
                .map(ledgerMapper::toDTO);
    }

    @Transactional
    public boolean deleteLedger(Long id) {
        if (ledgerRepo.existsById(id)) {
            ledgerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<LedgerDTO> findByUserId(Long userId) {
        return ledgerRepo.findByUserId(userId).stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LedgerDTO> findByUserIdAndCategoryId(Long userId, Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found w ID: ", categoryId));
        return ledgerRepo.findByUserIdAndCategory(userId, category).stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LedgerDTO> findByUserIdAndIsEssential(Long userId, Boolean isEssential) {
        return ledgerRepo.findByUserIdAndIsEssential(userId, isEssential).stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LedgerDTO> findByUserIdAndMonth(Long userId, YearMonth month) {
        return ledgerRepo.findByUserIdAndMonth(userId, month).stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LedgerDTO> findByUserIdAndMonthBetween(Long userId, YearMonth startMonth, YearMonth endMonth) {
        return ledgerRepo.findByUserIdAndMonthBetween(userId, startMonth, endMonth).stream()
                .map(ledgerMapper::toDTO)
                .collect(Collectors.toList());
    }

}
