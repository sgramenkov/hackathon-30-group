package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.ImagePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagePlaceRepository  extends JpaRepository<ImagePlace, Long> {
}
