package com.pFI.pFI_api.mapper;

import com.pFI.pFI_api.dto.LedgerUpdateDTO;
import com.pFI.pFI_api.dto.LedgerCreateDTO;
import com.pFI.pFI_api.dto.LedgerDTO;
import com.pFI.pFI_api.entity.Category;
import com.pFI.pFI_api.entity.Ledger;
import com.pFI.pFI_api.service.CategoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LedgerMapper {

    @Autowired
    protected CategoryService categoryService;

    @Mapping(target  = "userId", source  = "user.id")
    public abstract LedgerDTO toDTO(Ledger ledger);

    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "user", ignore = true)
    public abstract Ledger toEntity(LedgerCreateDTO dto );

    @Mapping(target = "category",  source =  "categoryId")
    public abstract void updateEntityFromDTO(LedgerUpdateDTO ledgerUpdateDTO, @MappingTarget Ledger ledger);

    protected Category mapCategoryIdToCategory(Long  categoryId) {
        if (categoryId  == null)  {
           return null;
        }
        return categoryService.findCategoryById(categoryId);
    }

    }