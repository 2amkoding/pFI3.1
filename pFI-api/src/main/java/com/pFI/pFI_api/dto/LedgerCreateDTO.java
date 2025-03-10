package com.pFI.pFI_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LedgerCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Month is required")
    private YearMonth month;

    @NotNull(message = "Category is required")
    private String category;

    @NotNull(message = "Essential flag is required")
    private Boolean isEssential;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

}
