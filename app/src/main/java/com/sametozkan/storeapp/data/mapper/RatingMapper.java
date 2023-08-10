package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.data.dto.RatingDTO;
import com.sametozkan.storeapp.domain.model.Rating;

public class RatingMapper {

    public static Rating toRating(RatingDTO ratingDTO){
        Rating rating = new Rating();
        rating.setRate(rating.getRate());
        rating.setCount(rating.getCount());
        return rating;
    }

    public static RatingDTO toRatingDTO(Rating rating){
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRate(ratingDTO.getRate());
        ratingDTO.setCount(ratingDTO.getCount());
        return ratingDTO;
    }
}
