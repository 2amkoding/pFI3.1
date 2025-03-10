package com.pFI.pFI_api.controller;

import com.pFI.pFI_api.dto.LedgerCreateDTO;
import com.pFI.pFI_api.dto.LedgerDTO;
import com.pFI.pFI_api.dto.LedgerUpdateDTO;
import com.pFI.pFI_api.service.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping
    public ResponseEntity<List<LedgerDTO>> getAllLedgers() {
        return ResponseEntity.ok(ledgerService.getAllLedgers());
    }

    @GetMapping
    public ResponseEntity<LedgerDTO> createLedger(@Valid @RequestBody LedgerCreateDTO ledgerCreateDTO) {
        LedgerDTO createdLedger = ledgerService.createLedger(ledgerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLedger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LedgerDTO> updateLedger(
            @PathVariable Long id,
            @Valid @RequestBody LedgerUpdateDTO ledgerUpdateDTO) {
        return ledgerService.updateLedger(id, ledgerUpdateDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ledger not found w ID: " + id));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLedger(@PathVariable Long id) {
        if (ledgerService.deleteLedger(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ledger not found with ID: " + id);
        }
    }

    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<LedgerDTO>> getLedgerByUserIdAndCategory(
            @PathVariable Long userId,
            @PathVariable String category) {
        List<LedgerDTO> ledgers = ledgerService.findByUserIdAndCategory(userId, category);
        return ResponseEntity.ok(ledgers);
    }

    @GetMapping("/user/{userId}/essential/{isEssential}")
    public ResponseEntity<List<LedgerDTO>> getLedgersByUserIdAndEssential(
            @PathVariable Long userId,
            @PathVariable Boolean isEssential) {
        List<LedgerDTO> ledgers = ledgerService.findByUserIdAndIsEssential(userId, isEssential);
        return ResponseEntity.ok(ledgers);
    }


    @GetMapping("/user/{userId}/month/{month}")
    public ResponseEntity<List<LedgerDTO>> getLedgersByUserIdAndMonth(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        List<LedgerDTO> ledgers = ledgerService.findByUserIdAndMonth(userId, month);
        return ResponseEntity.ok(ledgers);
    }

    @GetMapping("/user/{userId}/period")
    public ResponseEntity<List<LedgerDTO>> getLedgersByUserIdAndPeriod(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth startMonth,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth endMonth) {
        List<LedgerDTO> ledgers = ledgerService.findByUserIdAndMonthBetween(userId, startMonth, endMonth);
        return ResponseEntity.ok(ledgers);
    }


}

