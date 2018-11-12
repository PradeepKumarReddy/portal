import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { User } from '../../_models/index';
import { UserManagmentService, RegisterService } from '../../_services/index';

@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  p = 1;
  users: User[];
  userEnabled = true;
  constructor(private router: Router, private route: ActivatedRoute,
  private userManagmentService: UserManagmentService,
  private registerService: RegisterService) { }

  ngOnInit() {
  this.userEnabled = true;
  this.getAllApplicationUsers();
  }

  getAllApplicationUsers() {
    this.userManagmentService.getAllApplicationUsers().subscribe(
      (res: User[]) => {
       console.log(res);
       this.users = [...res];
       },
      err => console.error(err),
      () => console.log('loaded users successful')
    );
  }

  disableUser(regId: string, index: number) {
  this.userManagmentService.disableUser(regId).subscribe(
    (res: User) => {
       console.log(res);
       this.users[index] = res;
       },
      err => console.error(err),
      () => console.log('disabled user successful')
  );
  }

  enableUser(regId: string, index: number) {
  this.userManagmentService.enableUser(regId).subscribe(
  (res: User) => {
       console.log(res);
       this.users[index] = res;
       },
      err => console.error(err),
      () => console.log('enabled user successful')
  );
  }

  deleteUser(regId: string) {
  this.userManagmentService.deleteUser(regId).subscribe(
    (res: boolean) => {
       console.log(res);
       this.users = [];
       this.getAllApplicationUsers();
      },
      err => console.error(err),
      () => console.log('delete user successful')
  );
  }

  sendRegisterEmail(regId: string) {
    this.registerService.sendRegisterEmail(regId).subscribe(
    (res: boolean) => {
       console.log(res);
      },
      err => console.error(err),
      () => console.log('register email send succesfully')
  );
  }
}
