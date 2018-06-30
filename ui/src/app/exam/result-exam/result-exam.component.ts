import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, Question, UserExam,UserResponse } from '../../_models/index';
import { ExamService } from '../../_services/index';

@Component({
  selector: 'app-result-exam',
  templateUrl: './result-exam.component.html',
  styleUrls: ['./result-exam.component.css']
})
export class ResultExamComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute, 
  								private examService : ExamService) { }

  ngOnInit() {
  	
  }

	getUserExamByExamIdAndUsername(){
	
	}
}
