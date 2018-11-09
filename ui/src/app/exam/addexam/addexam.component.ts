import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, Question } from '../../_models/index';
import { AddExamService, AlertService } from '../../_services/index';

@Component({
  selector: 'app-addexam',
  templateUrl: './addexam.component.html',
  styleUrls: ['./addexam.component.css']
})
export class AddexamComponent implements OnInit {
  exam: Exam;
  questions: Question[];

  constructor(private router: Router, private route: ActivatedRoute, private addExamService: AddExamService,
  private alertService: AlertService) { }

  ngOnInit() {
  this.exam = new Exam();
  this.exam.questions = new Array<Question>();
  this.addExamService.getAllQuestions().subscribe(
      (res: Question[]) => {
       console.log(res);
       this.questions = [...res];
       },
      (err) => {
        console.error(err);
        this.alertService.error('Some thing went wrong, try again');
      },
      () => console.log('loaded Questions successful')
    );
   }
  createExam() {
  this.addExamService.addExam(this.exam).subscribe(
      (res: Exam) => {
       console.log(res);
       this.alertService.success('Exam created successfully');
       this.exam = new Exam();
       // this.questions = [...res];
       },
      (err) => {
        console.error(err);
        this.alertService.error('Some thing went wrong, try again');
      },
      () => console.log('loaded exam successful')
    );
  }
  addQestion(ques: Question, e) {
  if (e.target.checked) {
  this.exam.questions.push(ques);
  } else {
  for (let i = 0; i < this.exam.questions.length; i++) {
   if (this.exam.questions[i].id === ques.id) {
      this.exam.questions.splice(i, 1);
      break;
   }
   }
  }
  console.log(this.exam);
  }
}

