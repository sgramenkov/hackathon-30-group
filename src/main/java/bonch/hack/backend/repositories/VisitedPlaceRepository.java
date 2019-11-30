package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitedPlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = "SELECT * FROM places pl WHERE EXISTS (SELECT * FROM visited_places visit WHERE place_id = pl.id AND user_id = ?1)",
            nativeQuery = true)
    List<Place> getVisitedPlaces(long userId);
}
