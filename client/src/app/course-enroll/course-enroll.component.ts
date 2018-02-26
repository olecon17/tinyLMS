import {Component, OnInit} from '@angular/core';
import {UserService} from "../shared/user/user.service";
import {CourseService} from "../shared/course/course.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Subscription} from "rxjs/Subscription";
import {CourseEnrollService} from "../shared/enroll/enroll.service";

@Component({
  selector: 'app-course-enroll',
  templateUrl: './course-enroll.component.html',
  styleUrls: ['./course-enroll.component.css']
})
export class CourseEnrollComponent implements OnInit {

  enrolled: Array<any>;
  users: Array<any>;
  course: any = {};
  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private courseService: CourseService,
              private courseEnrollService: CourseEnrollService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {

      const courseId = params['id'];
      this.courseService.get(courseId).subscribe((course: any) => {
        if (course) {
          this.course = course;
        } else {
          console.log("Course not found");
        }
      });

      this.courseEnrollService.get(courseId).subscribe((enrolled: any) => {
        if (enrolled) {
          this.enrolled = enrolled;
        } else {
          console.log('no students');
        }
      });


      this.userService.getAll().subscribe((users: any) => {
        if (users) {
          const finalUsers = users.filter(user => {
            return (this.course.users.indexOf(user.userid) == -1)
          });

          this.users = finalUsers;
        } else {
          console.log("could not retrieve users")
        }
      });
    });


  }


  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  goToList() {
    this.router.navigate(['/courses']);
  }

  reloadList() {
    const courseId = this.course ? this.course.id : '';
    this.router.navigate(['/course-enroll/' + courseId])
  }


  save(form: NgForm) {
    this.courseEnrollService.save(form).subscribe(result => {

      this.reloadList();
    }, error => console.error(error))
  }

  remove(enrolled: any) {
    this.courseEnrollService.remove(enrolled).subscribe(result => {
      this.reloadList();
    }, error => console.error(error))
  }

}
