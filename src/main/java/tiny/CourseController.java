package tiny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping(path = "/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable <Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Course getOneCourse (@PathVariable int id) {
        return courseRepository.findOne(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Course addCourse(@RequestParam String name, @RequestParam String courseCode) {
        Course course = new Course();
        course.setCourseName(name);
        course.setCourseCode(courseCode);


        courseRepository.save(course);

        return course;
    }



    @PostMapping(path = "/edit")
    public @ResponseBody
    Course editCourse(@RequestBody Course request) {

        int courseId;
        courseId = request.getId();

        if (courseId == 0) {
            return addCourse(request.getCourseName(), request.getCourseCode());
        }


        Course foundCourse = courseRepository.findOne(courseId);


        if (!request.getCourseName().equals("")) {
            foundCourse.setCourseName(request.getCourseName());
        }

        if (!request.getCourseCode().equals("")) {
            foundCourse.setCourseCode(request.getCourseCode());
        }


        courseRepository.save(foundCourse);

        return foundCourse;

    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    int deleteCourse(@RequestBody String id) {
        int courseId = Integer.parseInt(id);

        courseRepository.delete(courseId);
        return courseId;

    }


}
