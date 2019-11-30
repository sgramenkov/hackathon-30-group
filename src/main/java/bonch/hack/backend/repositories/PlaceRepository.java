package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository  extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * FROM places pl WHERE NOT EXISTS (SELECT * FROM marked_places pl_mr WHERE place_id = pl.id AND user_id = ?1) AND pl.city LIKE %?2%",
            nativeQuery = true)
    List<Place> getPlace(long userId, String city);

    @Query(value = "SELECT * FROM places pl WHERE NOT EXISTS (SELECT * FROM marked_places pl_mr WHERE place_id = pl.id AND user_id = ?1) AND pl.city LIKE %?2% AND pl.type_place LIKE %?3%",
            nativeQuery = true)
    List<Place> getPlace(long userId, String city, String type);

    @Query(value = "SELECT * FROM places pl WHERE NOT EXISTS (SELECT * FROM marked_places pl_mr WHERE place_id = pl.id AND user_id = ?1) AND pl.city LIKE %?2% AND pl.type_place LIKE %?3% AND pl.is_free = ?4",
            nativeQuery = true)
    List<Place> getPlace(long userId, String city, String type, boolean isFree);

    @Query(value = "SELECT * FROM places pl WHERE EXISTS (SELECT * FROM marked_places pl_mr WHERE place_id = pl.id AND user_id = ?1  AND mark_pl.is_like = true)",
            nativeQuery = true)
    List<Place> getLikedPlaces(long userId);
}
