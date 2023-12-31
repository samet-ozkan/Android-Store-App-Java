package com.sametozkan.storeapp.util;

import java.io.Serializable;

public enum Categories implements Serializable {

    MENS_CLOTHING("men's clothing", "Men's Clothing"),

    WOMENS_CLOTHING("women's clothing", "Women's Clothing"),

    JEWELERY("jewelery", "Jewelery"),

    ELECTRONICS("electronics", "Electronics");

    public final String category;
    public final String categoryTitle;

    Categories(String category, String categoryTitle) {
        this.category = category;
        this.categoryTitle = categoryTitle;
    }
}
