import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Contactus } from '../_models/index';
import { environment } from '../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Headers': '*' })
  };
const API_URL = environment.apiUrl;

@Injectable()
export class ContactUsService {

  constructor(private http: HttpClient) { }
  getContactUs(): Observable<Contactus> {
  return this.http.get<Contactus>(API_URL + '/api/get/contactUs', httpOptions);
  }
}
