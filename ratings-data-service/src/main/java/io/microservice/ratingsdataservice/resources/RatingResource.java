package io.microservice.ratingsdataservice.resources;

import io.microservice.ratingsdataservice.models.Rating;
import io.microservice.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("rating")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId)
    {
      return new Rating(movieId,5);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId)
    {
         List<Rating> ratings = Arrays.asList(
            new Rating("100",2),
            new Rating("200",4),
            new Rating("300",6)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
         return userRating;
    }
}
