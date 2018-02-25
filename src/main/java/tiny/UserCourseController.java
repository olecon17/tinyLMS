package tiny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping(path = "/usercourse")
@CrossOrigin(origins = "http://localhost:4200")
public class UserCourseController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable <User> getCourseUsers(@RequestParam String courseId) {
        Course foundCourse = courseRepository.findOne(Integer.parseInt(courseId));

        Iterable <User> courseUsers = userRepository.findAll(foundCourse.getUsers());

        return courseUsers;

    }

    @PostMapping(path = "/add")
    public @ResponseBody
    User addUser(@RequestParam String courseId, @RequestParam String userId) {
        Course foundCourse = courseRepository.findOne(Integer.parseInt(courseId));
        User foundUser = userRepository.findOne(Integer.parseInt(userId));

        ArrayList<Integer> courseUsers = foundCourse.getUsers();

        if(!courseUsers.contains(foundUser)) {
            courseUsers.add(foundUser.getUserid());
        }

        foundCourse.setUsers(courseUsers);

        courseRepository.save(foundCourse);

        return foundUser;
    }


    @PostMapping(path = "/remove")
    public @ResponseBody
    Iterable <Integer> removeUser(@RequestParam String courseId, @RequestParam String userId) {
        Course foundCourse = courseRepository.findOne(Integer.parseInt(courseId));
        User foundUser = userRepository.findOne(Integer.parseInt(userId));

        ArrayList <Integer> courseUsers = foundCourse.getUsers();

        int index = courseUsers.indexOf(foundUser.getUserid());

        courseUsers.remove(index);

        foundCourse.setUsers(courseUsers);
        courseRepository.save(foundCourse);

        return foundCourse.getUsers();
    }

}
