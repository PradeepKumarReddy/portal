import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UserRegister, User, Exam, UserExam } from '../_models/index';
import { environment } from '../../environments/environment';

const API_URL = environment.apiUrl;
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };

@Injectable()
export class ReportsService {

  constructor(private http: HttpClient) { }

  getCompletedExamsByUser(username: string): Observable<Exam[]> {
  return this.http.get<Exam[]>(API_URL + '/api/examsList/completed/' + username, httpOptions);
  }
}
