import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService, AuthService, ResetPasswordService } from '../_services/index';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: any = {};
    registerModel: any = {};
    loading = false;
    error = '';
    showModel = false;
    resetFailed = false;
    constructor(
        private router: Router,
        private authenticationService: AuthService,
        private resetPasswordService: ResetPasswordService) { }
    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }
    login_old() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(result => {
                if (result === true) {
                    // login successful
                    this.router.navigate(['/']);
                } else {
                    // login failed
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }
            });
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
            (result : boolean) => {
                 if (result === true) {
                    // login successful
                    this.router.navigate(['/']);
                } else {
                    // login failed
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }
            },
            (err) => {
                console.error(err);
                this.error = 'Username or password is incorrect';
                    this.loading = false;
            },
            () => console.log('getResetUser successful')
            );
    }
    
    gotoRegister() {
        this.router.navigate(['register']);
    }
    resetPass(username: string) {
    
    if(username === undefined || username === null) {
        // write validation
        this.resetFailed = true;
    } else {
        this.resetPasswordService.resetPasswordEmail(username)
            .subscribe(result => {
                if (result === true) {
                    console.log(result);
                    this.showModel = true;
                    this.resetFailed = false;
                } else {
                    
                }
        });
    }
    }
}
