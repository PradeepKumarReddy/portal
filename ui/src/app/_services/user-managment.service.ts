import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../_models/index';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class UserManagmentService {

  constructor(private http: HttpClient) { }

  getAllApplicationUsers(): Observable<User[]> {
  return this.http.get<User[]>(API_URL + '/api/get/allUsers', httpOptions);
  }
  getAllEnabledApplicationUsers(): Observable<User[]> {
  return this.http.get<User[]>(API_URL + '/api/get/allEnabledUsers', httpOptions);
  }
  disableUser(regId: string): Observable<User> {
  console.log(' regId ' + regId);
  return this.http.post<User>(API_URL + '/api/disableUser/' + regId, httpOptions);
  }
  enableUser(regId: string): Observable<User> {
  console.log(' regId ' + regId);
  return this.http.post<User>(API_URL + '/api/enableUser/' + regId, httpOptions);
  }
  deleteUser(regId: string): Observable<boolean> {
  console.log(' regId ' + regId);
  return this.http.post<boolean>(API_URL + '/api/deleteUser/' + regId, httpOptions);
  }
}
