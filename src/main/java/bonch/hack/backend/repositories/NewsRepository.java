package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
