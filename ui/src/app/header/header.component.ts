import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { GlobalService, AuthService, RegisterService } from '../_services/index';
import { UserRegister, User } from '../_models/index';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
user: User;
regId: string;
isAdminUserLoggedIn$: Observable<boolean>;
isLoggedIn$: Observable<boolean>;
constructor(private router: Router, private route: ActivatedRoute,
public app: GlobalService,
public authService: AuthService,
public registerService: RegisterService) { }
ngOnInit() {
	this.isAdminUserLoggedIn$ = this.authService.isAdminUserLoggedIn;
	this.isLoggedIn$ = this.authService.isLoggedIn;
}
logout() {
// console.log("logout");
this.authService.logout();
this.router.navigate(['/']);
}
}
