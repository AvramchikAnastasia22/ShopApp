package shop.repository;

import org.springframework.stereotype.Repository;
import shop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface repository_employee extends JpaRepository<Employee,Integer> {
}
