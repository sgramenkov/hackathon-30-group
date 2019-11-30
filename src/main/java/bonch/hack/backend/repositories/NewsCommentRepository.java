package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.NewsComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
}
