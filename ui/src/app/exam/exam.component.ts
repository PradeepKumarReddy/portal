import { Component, OnInit } from '@angular/core';
import { Question, QuestionOption } from "../_models/index";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExamService } from '../_services/index';
import { Exam } from '../_models/index';

import { TimerComponent } from '../timer/timer.component';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  
//  p : number = 1;
//  exam : Exam
//  questions : Question[] = [];

  examStarted: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, 
  								private examService : ExamService) {
  	this.route.params.subscribe( params => console.log(params) );
  }

  ngOnInit() {
  }

  startExam(examId: number, username : string) {
  	this.examService.saveUserExam(examId, username).subscribe(
      (res : any) => {
       console.log(res);
       },
      err => console.error(err),
      () => console.log('loaded Exam successful')
    );
  	this.examStarted = true;
  	this.router.navigate(['view-exam', examId], {relativeTo : this.route});
  }

  endExam() {
  	this.examStarted = false;
  }
}
