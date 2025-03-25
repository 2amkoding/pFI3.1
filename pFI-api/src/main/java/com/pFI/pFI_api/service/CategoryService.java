package com.pFI.pFI_api.service;

import com.pFI.pFI_api.dto.CategoryCreateDTO;
import com.pFI.pFI_api.dto.CategoryDTO;
import com.pFI.pFI_api.dto.CategoryUpdateDTO;
import com.pFI.pFI_api.entity.Category;
import com.pFI.pFI_api.mapper.CategoryMapper;
import com.pFI.pFI_api.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .map(categoryMapper::toDTO);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO)  {
        Category category = categoryMapper.toEntity(categoryCreateDTO);
        category = categoryRepo.save(category);
        return categoryMapper.toDTO(category);
    }

    @Transactional
    public Optional<CategoryDTO> updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        return categoryRepo.findById(id)
                .map(category -> {
                    categoryMapper.updateEntityFromDTO(categoryUpdateDTO, category);
                    return categoryRepo.save(category);
                })
                .map(categoryMapper::toDTO);
    }

    @Transactional
    public boolean deleteCategory(Long id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Category findCategoryById(Long  id) {
        return categoryRepo.findById(id)
                .orElseThrow(()  -> new IllegalArgumentException("Category  not found with ID: " + id));
    }

}
