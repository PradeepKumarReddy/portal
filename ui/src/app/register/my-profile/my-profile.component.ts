import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserRegister, User } from '../../_models/index';
import { RegisterService, AlertService, GlobalService } from '../../_services/index';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  registerModel: UserRegister;
  constructor(private router: Router, private route: ActivatedRoute,
  private registerService: RegisterService,
  private alertService: AlertService,
  private globalService: GlobalService) { }

  ngOnInit() {
  this.registerModel = new UserRegister();
  this.getUserDetails();
  }

  getUserDetails() {
  var regId = this.globalService.localStorageItem('currentUser');
  this.registerService.getRegisterUserByRegId(regId)
  .then(
    (userRegister: UserRegister) => {
   // this.registerSuccess = true;
    this.registerModel = userRegister;
    })
  .catch(
    (err) => {
    console.error(err);
    }
  );
  }

}
