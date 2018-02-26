import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../shared/course/course.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit {

  course: any = {};
  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private courseService: CourseService) { }



  ngOnInit() {
    this.sub = this.route.params.subscribe( params => {
      const id = params['id'];
      if (id) {
        this.courseService.get(id).subscribe((course: any) => {
          if (course) {
            this.course = course;
          } else {
            console.log("Course not found");
            this.gotoList();
          }
        })
      }
    })
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/course-list'])
  }

  save(form: NgForm) {
    this.courseService.save(form).subscribe( result => {
      this.gotoList();
    }, error => console.error(error))
  }

  remove(id: string) {
    this.courseService.remove(id).subscribe( result => {
      this.gotoList();
    }, error => console.error(error))
  }

}
