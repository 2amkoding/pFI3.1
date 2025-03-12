package com.pFI.pFI_api.mapper;

import com.pFI.pFI_api.dto.CategoryDTO;
import com.pFI.pFI_api.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel =  = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryCreateDTO categoryCreateDTO);

    void updateEntityFromDTO(CategoryUpdateDTO categoryUpdateDTO, @MappingTarget Category category);
}
