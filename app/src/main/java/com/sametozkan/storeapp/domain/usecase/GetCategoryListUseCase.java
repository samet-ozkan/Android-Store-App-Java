package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.data.mapper.CategoryListMapper;
import com.sametozkan.storeapp.domain.repository.CategoryRepository;
import com.sametozkan.storeapp.util.Categories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetCategoryListUseCase {

    private CategoryRepository categoryRepository;

    @Inject
    public GetCategoryListUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Single<List<String>> execute() {
        return categoryRepository.getCategoryList();
    }
}
