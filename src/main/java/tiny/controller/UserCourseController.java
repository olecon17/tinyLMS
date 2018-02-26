package tiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tiny.model.Course;
import tiny.repository.CourseRepository;
import tiny.model.User;
import tiny.repository.UserRepository;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping(path = "/usercourse")
@CrossOrigin(origins = "http://localhost:4200")
public class UserCourseController {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/list/{id}")
    public @ResponseBody
    Iterable<User> getCourseUsers(@PathVariable("id") String id) {
        Course foundCourse = courseRepository.findOne(Integer.parseInt(id));

        Iterable<User> courseUsers = userRepository.findAll(foundCourse.getUsers());

        return courseUsers;

    }

    @PostMapping(path = "/add")
    public @ResponseBody
    User addUser(@RequestBody Map<String, String> json) {
        // use map to accomodate reuqest body with two values

        int userId = Integer.parseInt(json.get("userId"));
        Course foundCourse = courseRepository.findOne(Integer.parseInt(json.get("courseId")));
        User foundUser = userRepository.findOne(userId);

        ArrayList<Integer> courseUsers = foundCourse.getUsers();

        if (!courseUsers.contains(userId)) {
            courseUsers.add(foundUser.getUserid());
        }

        foundCourse.setUsers(courseUsers);

        courseRepository.save(foundCourse);

        return foundUser;
    }


    @PostMapping(path = "/remove")
    public @ResponseBody
    Iterable<Integer> removeUser(@RequestBody Map<String, String> json) {

        Course foundCourse = courseRepository.findOne(Integer.parseInt(json.get("courseId")));
        User foundUser = userRepository.findOne(Integer.parseInt(json.get("userId")));

        ArrayList<Integer> courseUsers = foundCourse.getUsers();

        int index = courseUsers.indexOf(foundUser.getUserid());

        courseUsers.remove(index);

        foundCourse.setUsers(courseUsers);

        courseRepository.save(foundCourse);

        return foundCourse.getUsers();
    }

}
