import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Exam, Question } from '../_models/index';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class AddExamService {
  questions: Question[] = [];
  constructor(private http: HttpClient) {}
  addExam(exam: Exam): Observable<Exam>  {
  console.log('exam created');
  console.log(exam);
  return this.http.post(API_URL + '/api/exam/add', exam, httpOptions);
  }
  getAllQuestions(): Observable<Question[]> {
  return this.http.get<Question[]>(API_URL + '/api/allQuestions', httpOptions);
  }
}
