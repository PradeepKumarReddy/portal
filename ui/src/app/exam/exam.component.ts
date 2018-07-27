import { Component, OnInit } from '@angular/core';
import { Question, QuestionOption } from '../_models/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExamService, GlobalService, AddExamService } from '../_services/index';
import { Exam } from '../_models/index';

import { TimerComponent } from '../timer/timer.component';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

examStarted = false;
exams: Exam[];
registrationId: string;
  constructor(private router: Router, private route: ActivatedRoute,
  private examService: ExamService, private globalService: GlobalService,
  private addExamService: AddExamService) {
  this.route.params.subscribe( params => console.log(params) );
  }

  ngOnInit() {
  this.registrationId = this.globalService.localStorageItem('currentUser');
  this.addExamService.getActiveExams(this.registrationId).subscribe(
    (res: Exam[]) => {
       console.log(res);
       this.exams = [...res];
       },
      err => console.error(err),
      () => console.log('loaded Exams successful')
  );
  }

  startExam(examId: number, username: string) {
  this.examService.saveUserExam(examId, username).subscribe(
      (res: any) => {
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
