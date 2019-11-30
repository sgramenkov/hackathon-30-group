package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<Chat, Long> {
}
