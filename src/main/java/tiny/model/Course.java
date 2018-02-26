package tiny.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course implements Serializable {

    @Id
    @Column(name = "courseId")
    @GeneratedValue
    private int id;

    @Column(name = "courseCode")
    private String courseCode;

    @Column(name = "name")
    private String courseName;

    @Column(name = "users")
    private ArrayList<Integer> users = new ArrayList<>();

    public Course() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Integer> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Integer> users) {
        this.users = users;
    }
}