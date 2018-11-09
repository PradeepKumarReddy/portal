import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam } from '../../_models/index';
import { ReportsService } from '../../_services/index';

@Component({
  selector: 'app-exam-report-details',
  templateUrl: './exam-report-details.component.html',
  styleUrls: ['./exam-report-details.component.css']
})
export class ExamReportDetailsComponent implements OnInit {
  p = 1;
  exams: Exam[];
  registrationId: string;
  constructor(private router: Router, private route: ActivatedRoute,
  private reportsService: ReportsService) { 
  }

  ngOnInit() {
  this.route.params.subscribe((params: Params) => {	
  this.registrationId = params['registrationId'];
  });
  this.getCompletedExams(this.registrationId);
  }

  getCompletedExams(regId: string) {
  	this.reportsService.getCompletedExamsByUser(regId).subscribe(
      (res: Exam[]) => {
       console.log(res);
       this.exams = [...res];
       },
      err => console.error(err),
      () => console.log('loaded completed exams successful')
    );
  }

  goBack() {
    this.router.navigate(['user-exam-report']);
  }
}
