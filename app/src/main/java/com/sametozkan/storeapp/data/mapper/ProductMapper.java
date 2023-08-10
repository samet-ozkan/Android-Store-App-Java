package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.data.dto.ProductDTO;
import com.sametozkan.storeapp.domain.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setPrice(productDTO.getPrice());
        product.setTitle(productDTO.getTitle());
        product.setRating(RatingMapper.toRating(productDTO.getRating()));
        return product;
    }

    public static List<Product> toProductList(List<ProductDTO> productDTOList) {
        List<Product> productList = productDTOList.stream()
                .map(ProductMapper::toProduct)
                .collect(Collectors.toList());
        return productList;
    }

    /*public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCategory(product.getCategory());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setPrice(product.getPrice());
        productDTO.setTitle(product.getTitle());
        productDTO.setRating(RatingMapper.toRatingDTO(product.getRating()));
        return productDTO;
    }*/
}
