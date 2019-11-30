package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitedPlaceRepository extends JpaRepository<Place, Long> {
}
