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
export class ResetPasswordService {

  constructor(private http: HttpClient) { }

  resetPasswordEmail(registrationId: string): Observable<boolean> {
    console.log(" regId "+ registrationId);
    return this.http.post<boolean>(API_URL + '/api/resetPasswordEmail/' + registrationId, httpOptions);
  }

  getResetUser(token: string): Observable<User> {
    return this.http.post<User>(API_URL + '/api/reset?token=' + token, httpOptions);
  }

  updateUserPassword(user: User): Observable<User> {
  	return this.http.post<User>(API_URL + '/api/updatePassword', user, httpOptions);
  }
}
