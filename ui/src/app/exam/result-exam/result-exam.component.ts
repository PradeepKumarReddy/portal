import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, Question, UserExam, UserResponse } from '../../_models/index';
import { ExamService } from '../../_services/index';
@Component({
  selector: 'app-result-exam',
  templateUrl: './result-exam.component.html',
  styleUrls: ['./result-exam.component.css']
})
export class ResultExamComponent implements OnInit {
  userExamId: number;
  username: string;
  resultExam: Exam;
  resultQuestions: Question[] = [];
  constructor (private router: Router, private route: ActivatedRoute, private examService: ExamService) {
  this.route.params.subscribe( params => {
  // console.log(params);
  this.userExamId = params['userExamId'];
  this.username = params['username'];
  });
  this.viewExam(this.userExamId, this.username);
  }
  ngOnInit() {
  this.resultExam = new Exam();
  }

  viewExam(userExamId: number, username: string) {
  this.examService.getCompletedExamDetails(userExamId, username).subscribe(
      (res: Exam) => {
         this.resultExam = res;
         // this.resultExam.examName = res.examName;
         this.resultQuestions = [...res.questions];
         console.log(res);
       },
       err => console.error(err),
      () => console.log('loaded Exam successful')
    );
  }
}
