package bonch.hack.backend.repositories;

import bonch.hack.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "UPDATE users u SET u.phone = ?1, u.user_name = ?2, u.password = ?3, u.sex = ?4, u.age = ?5, u.user_img = ?6 WHERE u.id = ?7",
            nativeQuery = true)
    void updateData(String phone, String userName, String password, String sex, int age, byte[] img, long userId);

    @Query(value = "UPDATE users u SET u.user_geo = ?1 WHERE u.id = ?2",
            nativeQuery = true)
    void updateGEO(String geo, long userId);

    @Query(value = "SELECT u.user_geo FROM users u WHERE u.id = ?1", nativeQuery = true)
    List<String> getGeoFromDB(long userId);
}
