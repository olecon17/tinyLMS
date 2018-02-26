import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';


import { UserService } from './shared/user/user.service';
import { HttpClientModule } from '@angular/common/http';
import { UserListComponent} from "./user-list/user-list.component";
import { UserEditComponent } from './user-edit/user-edit.component';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { CourseListComponent } from './course-list/course-list.component';
import { CourseEditComponent } from './course-edit/course-edit.component';
import {CourseService} from "./shared/course/course.service";
import { HomeComponent } from './home/home.component';
import { CourseEnrollComponent } from './course-enroll/course-enroll.component';
import {CourseEnrollService} from "./shared/enroll/enroll.service";



const appRoutes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'user-list',
    component: UserListComponent
  },
  {
    path: 'user-add',
    component: UserEditComponent
  },
  {
    path: 'user-edit/:id',
    component: UserEditComponent
  },
  {
    path: 'course-list',
    component: CourseListComponent
  },
  {
    path: 'course-add',
    component: CourseEditComponent
  },
  {
    path: 'course-edit/:id',
    component: CourseEditComponent
  },
  {
    path: 'course-enroll/:id',
    component: CourseEnrollComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserEditComponent,
    CourseListComponent,
    CourseEditComponent,
    HomeComponent,
    CourseEnrollComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService, CourseService, CourseEnrollService],
  bootstrap: [AppComponent]
})
export class AppModule { }
