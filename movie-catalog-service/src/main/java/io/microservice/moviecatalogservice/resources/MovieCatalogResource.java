package io.microservice.moviecatalogservice.resources;

import io.microservice.moviecatalogservice.models.CatalogItem;

import io.microservice.moviecatalogservice.models.Movie;
import io.microservice.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId)
    {
        WebClient.Builder builder = WebClient.builder();

        //get all reted movie IDs
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/rating/users/"+userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
            /*Movie movie = webClientBuilder.build().
                    get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/

            return new CatalogItem(movie.getName(),"test",rating.getRating());
                }

        ).collect(Collectors.toList());

        //for each movie ID, call movie info service and get details

        // put them all together
    }
}
