import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject, SubResource } from '../_models/index';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class SubjectService {

  constructor(private http: HttpClient) { }
  getSubjectByNameAndType(name: string, type: string): Observable<Subject> {
  return this.http.get<Subject>(API_URL + '/api/get/subject/' + name +
  '/' + type, httpOptions);
  }

  getAllSubjectByType(type: string): Observable<Subject[]> {
  return this.http.get<Subject[]>(API_URL + '/api/get/allSubjects/' + type, httpOptions);
  }

  getAllSubjects(): Observable<Subject[]> {
  return this.http.get<Subject[]>(API_URL + '/api/get/subjects', httpOptions);
  }

  addSubResource(subResource: SubResource): Observable<SubResource> {
  return this.http.post<SubResource>(API_URL + '/api/subResource/add', subResource, httpOptions);
  }
}
