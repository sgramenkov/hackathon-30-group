package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.MarkedPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarkedPlaceRepository  extends JpaRepository<MarkedPlace, Long> {
    @Query(value = "DELETE FROM marked_places mark_pl WHERE mark_pl.place_id = ?1 AND mark_pl.user_id = ?2 AND mark_pl.is_like = true",
            nativeQuery = true)
    void deleteLikedPlace(long placeId, long userId);
}
