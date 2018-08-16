import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserRegister, User } from '../_models/index';
import { RegisterService, AlertService } from '../_services/index';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerModel: UserRegister;
  markReadonly = false;
  registerSuccess = false;
  registerFailure = false;
  registrationId: string;
  signupUser: User;
  confirm_password: string;
  loading = false;
  error = '';

  constructor (private router: Router, private route: ActivatedRoute,
  private registerService: RegisterService,
  private alertService: AlertService) {
    this.route.params.subscribe( params =>
    console.log(params)
    );
  }
  ngOnInit() {
  this.registerModel = new UserRegister();
  this.signupUser = new User();
  }

  register() {
    console.log(this.registerModel);
    this.loading = true;
    this.registerService.createRegisterUser(this.registerModel)
    .then( (user: UserRegister) => {
      console.log(user);
      this.registerService.getRegisterUser(user.id)
      .then(
        (userRegister: UserRegister) => {
        this.registerSuccess = true;
        this.registerModel = new UserRegister();
        this.router.navigate(['register-success', userRegister.registrationId, userRegister.email],  {relativeTo : this.route}); }
        )
      .catch(
        (err) => {
        console.error(err);
        this.registerFailure = true;
        this.alertService.error('Some thing went wrong, try again');
        this.router.navigate(['register-failure'], {relativeTo : this.route});
        }
      );

    })
    .catch(
    (err: HttpErrorResponse) => {
    console.log(err);
        console.error(err.error.message);
        this.registerFailure = true;
        this.router.navigate(['register-failure'], {relativeTo : this.route});
        this.alertService.error(err.error.message);
      }
    );
  }
  gotoLogin() {
     this.router.navigate(['login']);
  }

  signup() {
    this.registerService.signup(this.signupUser)
    .subscribe(
      (res) => {
       console.log(res);
      },
      err => console.error(err),
      () => console.log('signup successful')
    );
  }

  copyResidentialAddress(event: any) {
    // console.log(event.srcElement.checked);
    if (event.srcElement.checked) {
      this.registerModel.permanentAddress = this.registerModel.residentialAddress;
      this.registerModel.permanentState = this.registerModel.residentialState;
      this.registerModel.permanentPincode = this.registerModel.residentialPincode;
      this.markReadonly = true;
    } else {
      this.registerModel.permanentAddress = '';
      this.registerModel.permanentState = '';
      this.registerModel.permanentPincode = '';
      this.markReadonly = false;
    }
  }
}
