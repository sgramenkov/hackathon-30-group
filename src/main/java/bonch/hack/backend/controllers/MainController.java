package bonch.hack.backend.controllers;


import bonch.hack.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final String DEFAULT_CITY = "Санкт-Петербург";
    private static final double DEFAULT_RATING = 7.5;
    private static final String SERVER_URL = "http://localhost:8080/";
    private static final String PATH_IMAGES = "images_places/";

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final MarkedPlaceRepository markedPlaceRepository;
    private final ImagePlaceRepository imagePlaceRepository;
    private final PlaceCommentRepository placeCommentRepository;
    private final UserRatingPlaceRepository userRatingPlaceRepository;

    @Autowired
    public MainController(PlaceRepository placeRepository, UserRepository userRepository, MarkedPlaceRepository markedPlaceRepository, ImagePlaceRepository imagePlaceRepository, PlaceCommentRepository placeCommentRepository, UserRatingPlaceRepository userRatingPlaceRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.markedPlaceRepository = markedPlaceRepository;
        this.imagePlaceRepository = imagePlaceRepository;
        this.placeCommentRepository = placeCommentRepository;
        this.userRatingPlaceRepository = userRatingPlaceRepository;
    }
}
