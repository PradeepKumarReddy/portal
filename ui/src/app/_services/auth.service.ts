import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs';
import 'rxjs/add/operator/catch';
import { User } from '../_models/index';
import { environment } from '../../environments/environment';
import { GlobalService } from './global.service';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;
@Injectable()
export class AuthService {
  public token: string;
  private adminUserLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient,
  private globalService: GlobalService) {
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
        console.log("login");
        console.log(response);
        const token = response.headers.get('authorization');
        if (token) {
            // set token property
            this.token = token;

            // store username and jwt token in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
            this.findUserByUsername();
            this.loggedIn.next(true);
            // return true to indicate successful login
            return true;
        } else {
            this.adminUserLoggedIn.next(false);
            this.loggedIn.next(false);
            // return false to indicate failed login
            return false;
        }
        })
        .catch((err: HttpErrorResponse) => {
            console.log("login error");
            this.adminUserLoggedIn.next(false);
            this.loggedIn.next(false);
            console.log(err);
            console.log(err.statusText);
            return Observable.throw(err.statusText);
        });
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        this.adminUserLoggedIn.next(false);
        this.loggedIn.next(false);
        localStorage.removeItem('currentUser');
    }

    get isAdminUserLoggedIn() {
    return this.adminUserLoggedIn.asObservable();
    }
    get isLoggedIn() {
    return this.loggedIn.asObservable();
    }
    findUserByUsername() {
    console.log('findUserByUsername');
    const registrationId = this.globalService.localStorageItem('currentUser');
    console.log(registrationId);
    this.isUserAdmin(registrationId).subscribe(
    (res: boolean) => {
      console.log(res);
           if (res) {
           console.log(res);
           this.adminUserLoggedIn.next(true);
           } else {
           this.adminUserLoggedIn.next(false);
           }
      },
      err => console.error(err),
      () => console.log('loaded isUserAdmin successful')
    );
    }
    isUserAdmin(regId: string): Observable<boolean> {
    return this.http.get<boolean>(API_URL + '/api/isUserAdmin/' + regId, httpOptions);
    }
}
