import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { User } from '../../_models/index';
import { UserManagmentService } from '../../_services/index';

@Component({
  selector: 'app-user-exam-report',
  templateUrl: './user-exam-report.component.html',
  styleUrls: ['./user-exam-report.component.css']
})
export class UserExamReportComponent implements OnInit {

  p = 1;
  users: User[];
  constructor(private router: Router, private route: ActivatedRoute,
  private userManagmentService: UserManagmentService) { }

  ngOnInit() {
  this.userManagmentService.getAllEnabledApplicationUsers().subscribe(
      (res: User[]) => {
       console.log(res);
       this.users = [...res];
       },
      err => console.error(err),
      () => console.log('loaded users successful')
    );
  }

}
