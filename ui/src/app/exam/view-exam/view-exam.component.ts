import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, Question, UserExam, UserResponse, QuestionOption } from '../../_models/index';
import { ExamService } from '../../_services/index';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };

@Component({
  selector: 'app-view-exam',
  templateUrl: './view-exam.component.html',
  styleUrls: ['./view-exam.component.css']
})
export class ViewExamComponent implements OnInit {

  p = 1;
  exam: Exam ;
  examId: number;
  questions: Question[] = [];
  userExam: UserExam;
  userResponse?: UserResponse;
  examSubmit: boolean;
  resultExam: Exam;
  resultQuestions: Question[] = [];

  constructor(private router: Router, private route: ActivatedRoute, private examService: ExamService) { }
  ngOnInit() {
  // this.examId = this.route.snapshot.params['examId'];
  this.examSubmit = false;
  this.route.params.subscribe((params: Params) => {
  this.examId = params['examId'];
  this.loadExam(this.examId);
  // this.loadUserExam(this.examId);
  });
  this.exam = new Exam();
  }
  onSelect(question , option) {
  question.isAnswered = false;
  question.options.forEach (function(element, index, array) {
  if (element.id !== option.id) {
        element.selected = false;
      }
  });
  question.options.forEach (function(element) {
  if (element.selected) {
      question.isAnswered = true;
    }
  });
  }
  gotoQuestion(i) {
  this.p = i + 1;
  }
  loadExam(examId: number) {
  this.examService.getExamById(examId).subscribe((exam: Exam) => {
    console.log(exam.questions);
    this.exam = exam;
    this.questions = [...exam.questions];
    },
    err => console.error(err),
      () => console.log('loaded Exam successful')
    );
  }
  endExam() {
    this.userExam = {};
    this.userExam.examId = this.examId;
    this.userExam.username = 'NA0001';
    this.userExam.userResponses = [];
    this.questions.forEach((question) => {
        this.userResponse = new UserResponse();
        this.userResponse.questionId = question.id;
        question.options.forEach(
          (option) => {
            if (option.selected) {
              this.userResponse.optionId = option.id;
            }
          }
        );
        this.userExam.userResponses.push(this.userResponse);
    });
    console.log(this.userExam);
   /*
    setTimeout( () => {
      this.examService.endUserExam(this.userExam).subscribe(
        (res : UserExam) => {
         console.log(res.username);
         this.resultExam = res;
         this.resultExam.userResponses = [...res.userResponses];
         },
        err => console.error(err),
        () => console.log('endExam successful')
      );
    }, 200);
   */

   setTimeout( () => {
      this.examService.endUserExam(this.userExam).subscribe(
        (res: Exam) => {
         this.resultExam = res;
         this.resultQuestions = [...res.questions];
         console.log(res);
         },
        err => console.error(err),
        () => console.log('endExam successful')
      );
    }, 200);
    console.log('questions' + this.questions);
    console.log('Exam Completed');
    this.examSubmit = true;
    // this.router.navigate(['exam/result-exam', this.examId]);
  }

 getStyle(option: QuestionOption) {
    console.log('getStyle');
    if (option.userSelect && option.answer) {
      return 'badge badge-success';
    } else if (option.answer) {
      return 'badge badge-success';
    } else if (option.userSelect) {
      return 'badge badge-warning';
    } else {
      return 'badge badge-light';
    }
 }
}
