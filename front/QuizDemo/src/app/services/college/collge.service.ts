import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CollgeService {

  readonly rootUrl = "http://localhost:5000";
  constructor(private http: HttpClient) { }

  addClg(body){
    return this.http.post(this.rootUrl+'/api/testset/addcollege',body);
  }
}
