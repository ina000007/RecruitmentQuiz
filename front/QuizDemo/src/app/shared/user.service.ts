import { Injectable } from '@angular/core';
import{ HttpClient, HttpHeaders } from '@angular/common/http';
import{ Response } from '@angular/http';
import{ Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  readonly rootUrl = "http://localhost:5000";
  constructor(private http:HttpClient) {
   }
   registerUser(user:User){
    const body={
      "username": user.Username,
      "password":user.Password,
      "email":user.Email,
      "name":user.Name
    }
    var reqHeader= new HttpHeaders({'No-Auth':'True'}); 
    return this.http.post(this.rootUrl+'/api/auth/signup',body,{headers:reqHeader}  );
   }
   userAuthentication(usernameOrEmail,password){
     const body={
      "usernameOrEmail":usernameOrEmail,
      "password":password
    };
    var reqHeader= new HttpHeaders({'No-Auth':'True'}); 
    return this.http.post(this.rootUrl+'/api/auth/signin',body,{headers:reqHeader}  );
   }
}
