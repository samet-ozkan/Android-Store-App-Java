package com.sametozkan.storeapp.domain.usecase;

import com.sametozkan.storeapp.domain.repository.CategoryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetCategoryListUseCase extends UseCase<Single<List<String>>, Void> {

    private CategoryRepository categoryRepository;

    @Inject
    public GetCategoryListUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Single<List<String>> execute(Void unused) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<List<String>> execute() {
        return categoryRepository.getCategoryList();
    }
}
