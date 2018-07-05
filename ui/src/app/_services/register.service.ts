import {Injectable} from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { UserRegister, User } from '../_models/index';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };

@Injectable()
export class RegisterService {
  constructor(private http: HttpClient) {}
  createRegisterUser(userRegister: UserRegister) {
  return this.http.post('http://localhost:8080/api/user/register', userRegister, httpOptions);
  }

  getRegisterUser(id: number): Observable<UserRegister> {
  return this.http.get<UserRegister>('http://localhost:8080/api/user/get/' + id, httpOptions);
  }

  signup(signupUser: User) {
  return this.http.post<User>('http://localhost:8080/users/sign-up', signupUser, httpOptions);
  }
}
