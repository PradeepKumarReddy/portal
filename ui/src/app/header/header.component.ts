import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { GlobalService, AuthService } from '../_services/index';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
constructor(private router: Router, private route: ActivatedRoute, public app: GlobalService, public authService: AuthService) { }
ngOnInit() {}
logout() {
    //console.log("logout");
  	this.authService.logout();
  	this.router.navigate(['/']);
 }
}
