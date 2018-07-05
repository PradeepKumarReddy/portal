import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Question } from '../_models/index';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };

@Injectable()
export class AddQuestionService {
  constructor(private http: HttpClient) { }

  addQuestion(question: Question) {
  console.log(question);
  return this.http.post('http://localhost:8080/api/question/add', question, httpOptions);
  }
}
