import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UserRegister, User } from '../_models/index';
import { environment } from '../../environments/environment';

const API_URL = environment.apiUrl;
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };

@Injectable()
export class RegisterService {
registerModel: UserRegister;
  constructor(private http: HttpClient) {}
  createRegisterUser_old(userRegister: UserRegister) {
  return this.http.post(API_URL + '/api/user/register', userRegister, httpOptions);
  }

  getRegisterUser_old(id: number): Observable<UserRegister> {
  return this.http.get<UserRegister>(API_URL + '/api/user/get/' + id, httpOptions);
  }

  signup(signupUser: User) {
  return this.http.post<User>(API_URL + '/users/sign-up', signupUser, httpOptions);
  }

  createRegisterUser(userRegister: UserRegister) {
  const promise = new Promise((resolve, reject) => {

   this.http.post(API_URL + '/api/user/register', userRegister, httpOptions)
        .toPromise()
        .then(
            (res: UserRegister) => { // Success
              this.registerModel = res;
              resolve(this.registerModel);
              console.log(  this.registerModel);
            },
            msg => { // Error
              reject(msg);
            }
        );
  });
  return promise;
  }
  getRegisterUser(id: number) {
  const promise = new Promise((resolve, reject) => {

   this.http.get<UserRegister>(API_URL + '/api/user/get/' + id, httpOptions)
        .toPromise()
        .then(
            (res: UserRegister) => { // Success
              this.registerModel = res;
              resolve(this.registerModel);
              console.log(  this.registerModel);
            },
            msg => { // Error
              reject(msg);
            }
        );
  });
  return promise;
  }
}
