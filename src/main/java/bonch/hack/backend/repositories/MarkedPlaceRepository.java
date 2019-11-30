package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.MarkedPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkedPlaceRepository  extends JpaRepository<MarkedPlace, Long> {
}
