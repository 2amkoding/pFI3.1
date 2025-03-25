package com.pFI.pFI_api.dto;

import com.pFI.pFI_api.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LedgerDTO {
    private Long id;
    private String name;
    private YearMonth month;
    private Category category;
    private Boolean isEssential;
    private BigDecimal amount;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
