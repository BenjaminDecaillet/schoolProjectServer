package hel.haagahelia.report.school.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface GradeRepository extends CrudRepository<Grade, Long> {

	List<Grade> findBySubject(@Param("subject") Subject subject);
	
}
