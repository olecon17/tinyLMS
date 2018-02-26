import { Component, OnInit, OnDestroy } from '@angular/core';



import { UserService } from '../shared/user/user.service';

import { NgForm } from '@angular/forms';
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit, OnDestroy {

  user: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.userService.get(id).subscribe((user: any) => {
          if (user) {
            this.user = user;
          } else {
            console.log(`User with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }

  save(form: NgForm) {
    this.userService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error))
  }

  remove(id: string) {
    this.userService.remove(id).subscribe(result => {
      this.gotoList();
    }, error => console.error(error))
  }



}
