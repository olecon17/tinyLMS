package tiny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping(path = "/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable <Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addCourse(@RequestParam String name) {
        Course course = new Course();
        course.setCourseName(name);


        courseRepository.save(course);

        return "Course created";
    }


    @PostMapping(path = "/edit")
    public @ResponseBody
    String editCourse(@RequestParam String courseCode, @RequestParam String courseName) {
        int courseId = Integer.parseInt(courseCode);

        Course foundCourse = courseRepository.findOne(courseId);

        if (!courseName.equals("")) {
            foundCourse.setCourseName(courseName);
        }

        courseRepository.save(foundCourse);

        return "Course modified";

    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    String deleteCourse(@RequestParam String courseCode) {
        int courseId = Integer.parseInt(courseCode);

        courseRepository.delete(courseId);
        return "Course deleted";

    }


}
