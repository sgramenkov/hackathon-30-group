package bonch.hack.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "place_geo_X", nullable = false)
    private double placeGeoX;

    @Column(name = "place_geo_Y", nullable = false)
    private double placeGeoY;

    @Column(name = "place_img", nullable = false)
    private String placeImg;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "is_free", nullable = false)
    private boolean isFree;

    @Column(name = "type_place", nullable = false)
    private String typePlace;

    @Column(name = "more_description", nullable = false)
    private String moreDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPlaceGeoX() {
        return placeGeoX;
    }

    public void setPlaceGeoX(double placeGeoX) {
        this.placeGeoX = placeGeoX;
    }

    public double getPlaceGeoY() {
        return placeGeoY;
    }

    public void setPlaceGeoY(double placeGeoY) {
        this.placeGeoY = placeGeoY;
    }

    public String getPlaceImg() {
        return placeImg;
    }

    public void setPlaceImg(String placeImg) {
        this.placeImg = placeImg;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getTypePlace() {
        return typePlace;
    }

    public void setTypePlace(String typePlace) {
        this.typePlace = typePlace;
    }

    public String getMoreDescription() {
        return moreDescription;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }
}