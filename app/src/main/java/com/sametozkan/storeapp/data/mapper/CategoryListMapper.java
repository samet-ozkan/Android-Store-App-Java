package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.util.Categories;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryListMapper {

    public static List<Categories> toCategoryList(List<String> categoryList) {

        List<Categories> categories = categoryList.stream().map(categoryString -> {
            for (Categories category : Categories.values()) {
                if (categoryString.equals(category.category)) {
                    return category;
                }
            }
            return null;
        }).collect(Collectors.toList());

        return categories;
    }

}
