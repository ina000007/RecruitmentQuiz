import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestSetService {

  readonly rootUrl = "http://localhost:5000";
  constructor(private http: HttpClient) { }
  
  createTestSet(body){
    return this.http.post(this.rootUrl+'/api/testset/createtest ',body);
  }

}
