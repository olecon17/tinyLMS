package tiny.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import tiny.model.Course;

@CrossOrigin(origins = "http://localhost:4200")
public interface CourseRepository extends CrudRepository<Course, Integer> {
}
