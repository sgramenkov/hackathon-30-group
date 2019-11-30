package bonch.hack.backend.controllers;

import bonch.hack.backend.entities.User;
import bonch.hack.backend.repositories.MarkedPlaceRepository;
import bonch.hack.backend.repositories.PlaceRepository;
import bonch.hack.backend.repositories.UserRepository;
import bonch.hack.backend.repositories.VisitedPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static bonch.hack.backend.JsonSingleton.getJSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ProfileController {

    private final UserRepository userRepository;
    private final MarkedPlaceRepository markedPlaceRepository;
    private final VisitedPlaceRepository visitedPlaceRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public ProfileController(UserRepository userRepository, MarkedPlaceRepository markedPlaceRepository, VisitedPlaceRepository visitedPlaceRepository, PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.markedPlaceRepository = markedPlaceRepository;
        this.visitedPlaceRepository = visitedPlaceRepository;
        this.placeRepository = placeRepository;
    }

    //POST create user
    @RequestMapping(value = "/users", method = POST)
    @ResponseBody
    public HttpStatus createUser(
            @RequestParam("phone") String phone,
            @RequestParam("password") String password) {

        HttpStatus httpStatus;
        User user;
        try{
            user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            userRepository.save(user);

            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //POST update profile data
    @RequestMapping(value = "/users/{user_id}", method = POST)
    @ResponseBody
    public HttpStatus saveProfileData(
            @PathVariable("user_id") long userId,
            @RequestParam("phone") String phone,
            @RequestParam("user_name") String userName,
            @RequestParam("password") String password,
            @RequestParam("age") int age,
            @RequestParam("sex") String sex,
            @RequestParam("img") MultipartFile img) {

        HttpStatus httpStatus;
        try {
            userRepository.updateData(phone, userName, password, sex, age, img.getBytes(), userId);
            httpStatus = HttpStatus.OK;
        } catch (IOException e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //POST update geo user position
    @RequestMapping(
            value = "/users/geo/{user_id}",
            method = POST)
    @ResponseBody
    public HttpStatus updateGeo(
            @PathVariable("user_id") long user_id,
            @RequestParam("geo") String geo) {

        HttpStatus httpStatus;
        try{
            userRepository.updateGEO(geo, user_id);
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

    //GET get profile data
    @RequestMapping(
            value = "/users/{user_id}",
            method = GET)
    @ResponseBody
    public String getProfileData(@PathVariable("user_id") long userId) {
        return getJSON(userRepository.findById(userId).get());
    }

    //GET get liked places
    @RequestMapping(
            value = "/users/{user_id}/liked",
            method = GET)
    @ResponseBody
    public String getLikedPlace(@PathVariable("user_id") long userId) {
        return getJSON(placeRepository.getLikedPlaces(userId));
    }

    //GET get user geo
    @RequestMapping(
            value = "/users/geo/{user_id}",
            method = GET)
    @ResponseBody
    public String getGeo(@PathVariable("user_id") long userId) {
        return getJSON(userRepository.getGeoFromDB(userId));
    }


    //GET visited places
    @RequestMapping(
            value = "/users/visited/{user_id}",
            method = GET)
    @ResponseBody
    public String getVisitedPlaces(@PathVariable("user_id") long userId) {
        return getJSON(visitedPlaceRepository.getVisitedPlaces(userId));
    }


    //DELETE liked place
    @RequestMapping(
            value = "/users/{user_id}/places/{place_id}",
            method = DELETE)
    @ResponseBody
    public HttpStatus deleteVisitedPlace(@PathVariable("user_id") long userId, @PathVariable("place_id") long placeId) {

        HttpStatus httpStatus;
        try {
            markedPlaceRepository.deleteLikedPlace(placeId, userId);
            // TODO: 11/30/2019
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return httpStatus;
    }

}
