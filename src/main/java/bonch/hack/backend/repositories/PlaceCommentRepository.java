package bonch.hack.backend.repositories;


import bonch.hack.backend.entities.PlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCommentRepository extends JpaRepository<PlaceComment, Long> {}
