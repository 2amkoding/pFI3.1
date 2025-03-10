package com.pFI.pFI_api.mapper;

import com.pFI.pFI_api.dto.LedgerUpdateDTO;
import com.pFI.pFI_api.dto.LedgerCreateDTO;
import com.pFI.pFI_api.dto.LedgerDTO;
import com.pFI.pFI_api.entity.Ledger;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LedgerMapper {

    LedgerDTO toDTO(Ledger ledger);

    Ledger toEntity(LedgerCreateDTO);

    void updateEntityFromDTO(LedgerUpdateDTO ledgerUpdateDTO, @MappingTarget Ledger ledger);

    }