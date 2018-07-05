import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { UserRegister, User } from '../_models/index';
import { RegisterService } from '../_services/index';


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
  registrationId: string;
  signupUser: User;
  confirm_password: string;
  loading = false;
  error = '';

  constructor (private router: Router, private route: ActivatedRoute, private registerService: RegisterService) {
    this.route.params.subscribe( params => console.log(params) );
  }
  ngOnInit() {
  this.registerModel = new UserRegister();
  this.signupUser = new User();
  }

  register() {
  console.log(this.registerModel);
  this.loading = true;
  this.registerService.createRegisterUser(this.registerModel).subscribe(
      (userRegister: UserRegister) => {
       console.log(userRegister);
       this.registerModel = userRegister;
      },
      err => console.error(err),
      () => console.log('registeration successful')
    );
    setTimeout( () => {
    this.registerService.getRegisterUser(this.registerModel.id).subscribe(
     (userRegister: UserRegister) => {
       // this.registerModel = userRegister;
       // this.signupUser.username = this.registerModel.registrationId;
       // console.log("2" + this.registrationId);
       this.registerSuccess = true;
       this.registerModel = new UserRegister();
       // console.log("3" +this.registerModel.registrationId);
       // var paramRegId = this.registerModel.registrationId;
       // console.log("TEST" + paramRegId);
       // var paramEmail = this.registerModel.email;
       this.router.navigate(['register-success', userRegister.registrationId, userRegister.email],  {relativeTo : this.route}); },
       err => {
        console.error(err);
        this.router.navigate(['register-failure'], {relativeTo : this.route});
      },
      () => console.log('getRegisterUser successful')
    ); }, 500);

    console.log(this.registerSuccess);
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
