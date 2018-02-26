import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CourseEnrollService {

  public API = "http://localhost:8080";
  public ENROLL_API = this.API + '/usercourse';

  constructor(private http: HttpClient) {
  }

  get(id: string) {
    return this.http.get(this.ENROLL_API + '/list/' + id);
  }

  save(enrolled: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.ENROLL_API + '/add', enrolled);
    return result;
  }

  remove(enrolled: any) {
    let result: Observable<Object>;
    result = this.http.post(this.ENROLL_API + '/remove', enrolled);
    return result;
  }

}
