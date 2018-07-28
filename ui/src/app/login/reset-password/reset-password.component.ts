import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { User } from '../../_models/index';
import { RegisterService, ResetPasswordService } from '../../_services/index';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  registrationId: string;
  email: string;
  signupUser: User;
  confirm_password: string;
  token: string;
  constructor(private router: Router, private route: ActivatedRoute,
  private registerService: RegisterService,
  private resetPasswordService: ResetPasswordService) { 
  console.log('Called Constructor');
  this.route.queryParams.subscribe(params => {
      this.token = params['token'];
  });
  this.resetPasswordService.getResetUser(this.token).subscribe(
      (res : User) => {
         console.log(res.username);
         this.signupUser.username = res.username;
        },
        err => console.error(err),
        () => console.log('getResetUser successful')
    );
  }

  ngOnInit() {
  this.signupUser = new User();
  }
  resetPassword() {
  this.resetPasswordService.updateUserPassword(this.signupUser).subscribe(
      (res : User) => {
         console.log('User password updated successfully');
         
        },
        err => console.error(err),
        () => console.log('getResetUser successful')
    );
  
  }
}
