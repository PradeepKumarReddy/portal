import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { User } from '../_models/index';
import { environment } from '../../environments/environment';
const API_URL = environment.apiUrl;
@Injectable()
export class AuthService {
  public token: string;
  constructor(private http: HttpClient) {
  // set token if saved in local storage
  const currentUser = JSON.parse(localStorage.getItem('currentUser'));
  this.token = currentUser && currentUser.token;
  }

  login(username: string, password: string): Observable<boolean> {
        console.log('login called');
        return this.http.post(API_URL + '/login', JSON.stringify({ username: username, password: password }),
        { headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
        'Accept': 'application/json', 'Access-Control-Allow-Headers': 'Authorization, X-Auth-Token, X-Custom-Header, Content-Type',
        'Access-Control-Expose-Headers': 'Authorization'})
        , observe: 'response'
        })
        .map((response: HttpResponse<any>) => {
        // console.log(response.headers.keys());
        // console.log('authorization', response.headers.get('authorization'));
        // login successful if there's a jwt token in the response
        const token = response.headers.get('authorization');
        if (token) {
            // set token property
            this.token = token;

            // store username and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

            // return true to indicate successful login
            return true;
        } else {
            // return false to indicate failed login
            return false;
        }
        });
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}
