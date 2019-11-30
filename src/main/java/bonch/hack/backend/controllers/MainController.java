package bonch.hack.backend.controllers;


import bonch.hack.backend.entities.*;
import bonch.hack.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static bonch.hack.backend.JsonSingleton.getJSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class MainController {

    private static final String DEFAULT_CITY = "Санкт-Петербург";
    private static final double DEFAULT_RATING = 7.5;
    private static final String SERVER_URL = "http://130.211.123.86:8080/";
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

    //POST create new place *******************************************************************************
    @RequestMapping(value = "/places", method = RequestMethod.POST)
    public @ResponseBody
    HttpStatus createPlace(
            @RequestParam("place_name") String placeName,
            @RequestParam("description") String description,
            @RequestParam("city") String city,
            @RequestParam("type_place") String type,
            @RequestParam("place_geo_x") double geoX,
            @RequestParam("place_geo_y") double geoY,
            @RequestParam("place_img") MultipartFile img,
            @RequestParam("is_free") boolean isFree,
            @RequestParam("more_description") String moreDescription) {

        Place place;
        HttpStatus httpStatus;
        BufferedOutputStream stream;
        try {
            place = new Place();
            place.setPlaceName(placeName);
            place.setCity(city);
            place.setDescription(description);
            place.setMoreDescription(moreDescription);
            place.setRating(DEFAULT_RATING);
            place.setTypePlace(type);
            place.setFree(isFree);
            place.setPlaceGeoX(geoX);
            place.setPlaceGeoY(geoY);
            place.setPlaceImg(SERVER_URL + PATH_IMAGES + placeName.hashCode() + ".png");

            stream = new BufferedOutputStream(new FileOutputStream(new File(PATH_IMAGES + placeName.hashCode() + ".png")));
            stream.write(img.getBytes());
            stream.close();
            placeRepository.save(place);

            httpStatus = HttpStatus.OK;
        } catch (IOException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //POST create new photo for place *******************************************************************************
    @RequestMapping(value = "/places/{place_id}/images", method = RequestMethod.POST)
    public @ResponseBody
    HttpStatus setPlaceImages(
            @PathVariable("place_id") long placeId,
            @RequestParam("place_img") MultipartFile img,
            @RequestParam("name_img") String nameImg) {

        ImagePlace imagePlace;
        HttpStatus httpStatus;
        BufferedOutputStream stream;
        try {
            imagePlace = new ImagePlace();
            imagePlace.setPlace(placeRepository.findById(placeId).get());
            imagePlace.setImg(SERVER_URL + PATH_IMAGES + nameImg.hashCode() + ".png");
            imagePlace.setNameImg(nameImg);

            stream = new BufferedOutputStream(new FileOutputStream(new File(PATH_IMAGES + nameImg.hashCode() + ".png")));
            stream.write(img.getBytes());
            stream.close();
            imagePlaceRepository.save(imagePlace);

            httpStatus = HttpStatus.OK;
        } catch (IOException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }


    //GET place for start activity *******************************************************************************
    @RequestMapping(value = "/places", method = GET)
    public @ResponseBody
    String getPlaces(
            @RequestParam("user_id") long userId,
            @RequestParam("city") String city,
            @RequestParam("near") String near) {

        String result;
        if (city == null) {
            city = DEFAULT_CITY;
        }
        //near = geo юзера
        //near +- 10км
        //sql query with this parameters

        result = getJSON(placeRepository.getPlace(userId, city));
        return result;
    }

    @RequestMapping(
            value = "/places",
            params = {"user_id", "city", "type", "near"},
            method = GET)
    public @ResponseBody
    String getPlaces(
            @RequestParam("user_id") long userId,
            @RequestParam("city") String city,
            @RequestParam("type") String type,
            @RequestParam("near") String near) {

        String result;
        if (city == null) {
            city = DEFAULT_CITY;
        }
        result = getJSON(placeRepository.getPlace(userId, city, type));
        return result;
    }

    @RequestMapping(
            value = "/places",
            params = {"user_id", "city", "type", "is_free", "near"},
            method = GET)
    public @ResponseBody
    String getPlaces(
            @RequestParam("user_id") long userId,
            @RequestParam("city") String city,
            @RequestParam("type") String type,
            @RequestParam("is_free") boolean isFree,
            @RequestParam("near") String near) {

        String result;
        if (city == null) {
            city = DEFAULT_CITY;
        }
        result = getJSON(placeRepository.getPlace(userId, city, type, isFree));
        return result;
    }


    //POST set marked place as like or dislike *******************************************************************************
    @RequestMapping(value = "/places/{place_id}/mark", method = POST)
    public @ResponseBody
    HttpStatus markPlace(
            @PathVariable("place_id") long placeId,
            @RequestParam("user_id") long userId,
            @RequestParam("is_like") boolean isLike) {

        HttpStatus httpStatus;
        MarkedPlace markedPlace;
        try {
            markedPlace = new MarkedPlace();
            markedPlace.setUser(userRepository.findById(userId).get());
            markedPlace.setPlace(placeRepository.findById(placeId).get());
            markedPlace.setTimeStamp(System.currentTimeMillis() / 1000L);
            markedPlace.setLike(isLike);
            markedPlaceRepository.save(markedPlace);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }


    //GET images for more description request *******************************************************************************
    @RequestMapping(value = "/places/{place_id}/images", method = GET)
    public @ResponseBody
    String getMoreImages(@PathVariable("place_id") long placeId) {
        return getJSON(imagePlaceRepository.getImages(placeId));
    }


    //POST set comment place *******************************************************************************
    @RequestMapping(value = "/places/{place_id}/comment", method = POST)
    public @ResponseBody
    HttpStatus setComment(
            @PathVariable("place_id") long placeId,
            @RequestParam("user_id") long userId,
            @RequestParam("comment") String comment) {

        HttpStatus httpStatus;
        PlaceComment placeComment;
        try {
            placeComment = new PlaceComment();
            placeComment.setUser(userRepository.findById(userId).get());
            placeComment.setPlace(placeRepository.findById(placeId).get());
            placeComment.setComment(comment);
            placeComment.setTimeStamp(System.currentTimeMillis() / 1000L);
            placeCommentRepository.save(placeComment);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }


    //POST set rating place *******************************************************************************
    @RequestMapping(value = "/places/{place_id}/rating", method = POST)
    public @ResponseBody
    HttpStatus setRating(
            @PathVariable("place_id") long placeId,
            @RequestParam("user_id") long userId,
            @RequestParam("rating") double rating) {

        HttpStatus httpStatus;
        UserRatingPlace userRatingPlace;
        try {
            userRatingPlace = new UserRatingPlace();
            userRatingPlace.setUser(userRepository.findById(userId).get());
            userRatingPlace.setPlace(placeRepository.findById(placeId).get());
            userRatingPlace.setRating(rating);
            userRatingPlaceRepository.save(userRatingPlace);

            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }


}
