import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { User } from '../../_models/index';
import { RegisterService } from '../../_services/index';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
};

@Component({
  selector: 'app-register-success',
  templateUrl: './register-success.component.html',
  styleUrls: ['./register-success.component.css']
})

export class RegisterSuccessComponent implements OnInit {

  registrationId: string;
  email: string;
  signupUser: User;
  confirm_password: string;

  constructor(private router: Router, private route: ActivatedRoute, private registerService: RegisterService) {
  }

  ngOnInit() {
    this.signupUser = new User();
  	this.route.params.subscribe(
 		(params: Params) => {
		this.registrationId = params['registrationId'];
    this.email = params['email'];
    this.signupUser.username = this.registrationId;
		}
  	);
  }

  signup() {
    this.signupUser.username = this.registrationId;
    this.registerService.signup(this.signupUser)
    .subscribe(
      (res) => {
       console.log(res);
      },
      err => console.error(err),
      () => {
        console.log('signup successful');
        this.router.navigate(['login']);
      }
    );
  }


}
