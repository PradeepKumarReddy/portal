import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-register-failure',
  templateUrl: './register-failure.component.html',
  styleUrls: ['./register-failure.component.css']
})
export class RegisterFailureComponent implements OnInit {
  constructor(private router: Router) {
    // override the route reuse strategy
    // this.router.routeReuseStrategy.shouldReuseRoute = function() {
    //    return false;
    // };
  }

  ngOnInit() {
  }

}
