package shop.repository;

import org.springframework.stereotype.Repository;
import shop.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface repository_history extends JpaRepository<History,Integer> {
}
