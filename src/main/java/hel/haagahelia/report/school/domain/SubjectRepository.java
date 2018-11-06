package hel.haagahelia.report.school.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface SubjectRepository extends CrudRepository<Subject, Long> {

	List<Subject> findByStudent(@Param("student") Student student);
}
