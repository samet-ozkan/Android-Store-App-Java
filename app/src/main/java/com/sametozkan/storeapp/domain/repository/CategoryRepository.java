package com.sametozkan.storeapp.domain.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CategoryRepository {

    Single<List<String>> getCategoryList();

}
