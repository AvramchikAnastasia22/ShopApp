package shop.repository;

import org.springframework.stereotype.Repository;
import shop.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface repository_service extends JpaRepository<Service,Integer> {
}
