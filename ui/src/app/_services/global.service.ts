import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
const API_URL = environment.apiUrl;
@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  constructor() { }
  public localStorageItem(id: string): string {
  if (id === 'currentUser') {
  const obj = JSON.parse(localStorage.getItem(id));
  if (obj !== null) {
  return obj.username;
  } else {
    return '';
  }
  } else {
    return localStorage.getItem(id);
  }
  }

  public isUserLoggedIn(): boolean  {
  console.log('isUserLoggedIn');
  const obj = JSON.parse(localStorage.getItem('currentUser'));
  if (obj !== null) {
   return true;
  } else {
   return false;
  }
  }

  public isRoleAdmin(id: string): boolean {
  if (id === 'currentUser') {
      const obj = JSON.parse(localStorage.getItem(id));
      if (obj != null) {
         return obj.username;
      } else {
         return false;
      }
    } else {
      return false;
    }
  }
}
