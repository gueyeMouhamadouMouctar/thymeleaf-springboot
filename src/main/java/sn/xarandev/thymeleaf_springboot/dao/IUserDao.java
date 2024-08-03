package sn.xarandev.thymeleaf_springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sn.xarandev.thymeleaf_springboot.entities.UserEntity;

import java.util.Optional;

public interface IUserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailAndPassword(String email, String password);

    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity  u where u.email = :emailParam")
    UserEntity getUserByEmail(@Param("emailParam") String email);
}
