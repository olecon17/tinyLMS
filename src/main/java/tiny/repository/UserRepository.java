package tiny.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import tiny.model.User;

@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends CrudRepository<User, Integer> {

}
