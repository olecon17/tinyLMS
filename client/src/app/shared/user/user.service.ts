import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    let data = this.http.get('//localhost:8080/user/all');
    console.log('data: ' + data);
    console.log('data: ' +  typeof data);
    console.log('data: ' +  JSON.stringify(data));
    return data;
  }

}
