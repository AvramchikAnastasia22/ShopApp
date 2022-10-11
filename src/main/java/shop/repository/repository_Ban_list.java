package shop.repository;

import org.springframework.stereotype.Repository;
import shop.model.Ban_list;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface repository_Ban_list extends JpaRepository<Ban_list,Integer> {
}
