import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, Question } from '../../_models/index';
import { AddExamService } from '../../_services/index';

@Component({
  selector: 'app-addexam',
  templateUrl: './addexam.component.html',
  styleUrls: ['./addexam.component.css']
})
export class AddexamComponent implements OnInit {
  exam: Exam;
  questions: Question[];

  constructor(private router: Router, private route: ActivatedRoute, private addExamService: AddExamService) { }

  ngOnInit() {
  this.exam = new Exam();
  this.exam.questions = new Array<Question>();
  this.addExamService.getAllQuestions().subscribe(
      (res: Question[]) => {
       console.log(res);
       this.questions = [...res];
       },
      err => console.error(err),
      () => console.log('loaded Questions successful')
    );
  // this.questions = [{"id" : 1, "questionDesc": "Test", 'isAnswered': false},
  // {"id" : 2, "questionDesc": "Test2", 'isAnswered': false}];
  }
  createExam() {
  this.addExamService.addExam(this.exam);
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

