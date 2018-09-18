import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { Exam, UserExam } from '../_models/index';
import { environment } from '../../environments/environment';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class ExamService {
  constructor(private http: HttpClient) { }

  getExamById(examId: number): Observable<Exam> {
  return this.http.get<Exam>(API_URL + '/api/exam/get/' +  examId, httpOptions);
  }

  saveUserExam(examId: number, username: string): Observable<any> {
  return this.http.post(API_URL + '/api/userExam/add', JSON.stringify({ username: username, examId: examId }), httpOptions);
  }

  endUserExam(userExam: UserExam): Observable<Exam> {
  console.log('User service', userExam);
  return this.http.post(API_URL + '/api/userExam/update', userExam, httpOptions);
  }

  getUserExamByExamIdAndUsername(examId: number , username: string): Observable<UserExam> {
  return this.http.get<UserExam>(API_URL + '/api/userExam/' + examId + '/' + username, httpOptions);
  }

  getCompletedExams(userId: string): Observable<UserExam[]> {
  return this.http.get<UserExam[]>(API_URL + '/api/userExam/completed/' + userId, httpOptions);
  }

  getCompletedExamDetails(userExamId: number, username: string): Observable<Exam> {
  return this.http.get<Exam>(API_URL + '/api/userExam/completedDetails/' + userExamId + '/' + username, httpOptions);
  }
}
