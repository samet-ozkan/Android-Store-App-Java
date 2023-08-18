package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.data.dto.RatingDTO;
import com.sametozkan.storeapp.domain.model.Rating;

public class RatingMapper {

    public static Rating toRating(RatingDTO ratingDTO){
        Rating rating = new Rating();
        rating.setRate(ratingDTO.getRate());
        rating.setCount(ratingDTO.getCount());
        return rating;
    }

    public static RatingDTO toRatingDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRate(rating.getRate());
        ratingDTO.setCount(rating.getCount());
        return ratingDTO;
    }
}
