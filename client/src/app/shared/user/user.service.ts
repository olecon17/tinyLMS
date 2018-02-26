import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class UserService {

  public API = "http://localhost:8080";
  public USER_API = this.API + "/user";


  constructor(private http: HttpClient) {}


  get(id: string) {
    return this.http.get(this.USER_API + '/' + id);
  }

  getAll(): Observable<any> {
    let data = this.http.get(this.USER_API + "/all");
    return data;
  }

  save(user: any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.USER_API + '/edit', user);

    return result;
  }

  remove(id: string) {
    let result: Observable<Object>;
    result = this.http.post(this.USER_API + '/delete', id);

    return result;
  }

}
