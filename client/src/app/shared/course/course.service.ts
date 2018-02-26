import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CourseService {

  public API = "http://localhost:8080";
  public COURSE_API = this.API + "/course";

  constructor(private http: HttpClient) {}

  get(id: string) {

    return this.http.get(this.COURSE_API + '/' + id);
  }

  getAll(): Observable<any> {
    let data =  this.http.get(this.COURSE_API + '/all');
    return data;
  }

  save(course: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.COURSE_API + '/edit', course);

    return result;
  }

  remove(id: string) {


    let result: Observable<Object>;
    result = this.http.post(this.COURSE_API + '/delete', id);

    return result;
  }


}
