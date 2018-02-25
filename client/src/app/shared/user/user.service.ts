import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';



@Injectable()
export class UserService {

  public API = "http://localhost:8080";
  public USER_API = this.API + "/user";



  constructor(private http: HttpClient) { }


  get(id: string) {
    console.log('getting for id: ' + id);
    return this.http.get(this.USER_API + '/' + id);
  }

  getAll(): Observable<any> {
    let data = this.http.get(this.USER_API + "/all");
    console.log('data: ' + data);
    console.log('data: ' +  typeof data);
    console.log('data: ' +  JSON.stringify(data));
    return data;
  }

  save(user: any): Observable<any> {
    let result: Observable<Object>;


    console.log('user:   ' + JSON.stringify(user));
    result = this.http.post( this.USER_API + '/edit', user);

    return result;
  }

  remove(id: string) {
    console.log(id);

    let result: Observable<Object>;

    result = this.http.post(this.USER_API + '/delete', id);

    return result;
  }

}
