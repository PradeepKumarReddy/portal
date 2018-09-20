import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Question } from '../_models/index';
import { environment } from '../../environments/environment';

const httpOptions_file = {
    headers: new HttpHeaders({
    'Accept': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class AddQuestionService {
  constructor(private http: HttpClient) { }

  addQuestion(question: Question) {
  console.log(question);
  return this.http.post(API_URL + '/api/question/add', question, httpOptions);
  }

  uploadQuestions(file: File): Observable<any> {
  const formData: FormData = new FormData();
  formData.append('file', file, file.name);
  return this.http.post<any>(API_URL + '/api/questions/upload', formData, httpOptions_file);
  }
}
