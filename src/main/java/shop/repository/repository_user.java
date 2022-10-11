package shop.repository;

import shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repository_user extends JpaRepository<User,Integer> {
}
