package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.ImagePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImagePlaceRepository  extends JpaRepository<ImagePlace, Long> {

    @Query(value = "SELECT img.img FROM images_places img WHERE img.place_id = ?1", nativeQuery = true)
    List<String> getImages(long placeId);
}
