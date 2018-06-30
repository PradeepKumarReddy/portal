import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, UserExam } from '../_models/index';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers':'*' })
  };

@Injectable()
export class ExamService {
  
  constructor(private http: HttpClient) { }

  getExamById(examId : number) : Observable<Exam> {
  	return this.http.get<Exam>("http://localhost:8080/api/exam/get/"+examId, httpOptions);
  }

  saveUserExam(examId : number, username : string) : Observable<any> {
  	return this.http.post("http://localhost:8080/api/userExam/add", JSON.stringify({ username: username, examId: examId }), httpOptions);
  }

  endUserExam(userExam : UserExam) : Observable<Exam> {
    console.log("User service", userExam);
    return this.http.post("http://localhost:8080/api/userExam/update", userExam, httpOptions);
  }

  getUserExamByExamIdAndUsername(examId : number , username: string) : Observable<UserExam> {
    return this.http.get<UserExam>("http://localhost:8080/api/userExam/"+examId+"/"+username, httpOptions);
  }
  
}
