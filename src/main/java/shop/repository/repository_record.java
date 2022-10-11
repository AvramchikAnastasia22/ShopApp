package shop.repository;

import org.springframework.stereotype.Repository;
import shop.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface repository_record extends JpaRepository<Records,Integer> {
}
