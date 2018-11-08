package hel.haagahelia.report.school.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Student, Long> {

	Student findByUsername(@Param("username") String username);
}
