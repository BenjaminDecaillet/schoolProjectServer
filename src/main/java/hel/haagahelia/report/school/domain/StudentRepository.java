package hel.haagahelia.report.school.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface StudentRepository extends CrudRepository<Student, Long> {

	Student findByUsername(@Param("username") String username);
}
