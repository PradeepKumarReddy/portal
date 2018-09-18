import { Component, OnInit } from '@angular/core';
import { Question, QuestionOption, Exam, UserExam } from '../../_models/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExamService, GlobalService, AddExamService } from '../../_services/index';

@Component({
  selector: 'app-completed-exams',
  templateUrl: './completed-exams.component.html',
  styleUrls: ['./completed-exams.component.css']
})
export class CompletedExamsComponent implements OnInit {
  p = 1;
  completedExams: UserExam[];
  registrationId: string;
  constructor(private router: Router, private route: ActivatedRoute,
  private examService: ExamService, private globalService: GlobalService,
  private addExamService: AddExamService) { }

  ngOnInit() {
  console.log('CompletedExamsComponent ngOnInit');
  this.registrationId = this.globalService.localStorageItem('currentUser');
  this.examService.getCompletedExams(this.registrationId).subscribe(
    (res: UserExam[]) => {
       console.log(res);
       this.completedExams = [...res];
       },
      err => console.error(err),
      () => console.log('loaded Completed Exams successful')
  );
  }

  viewExam(userExamId: number, username: string) {
  this.router.navigate(['result-exam', userExamId, username]);
  }

}
