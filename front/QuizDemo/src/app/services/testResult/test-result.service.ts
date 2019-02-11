import { HttpClient } from '@angular/common/http';
import { Constants } from './../../constants';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestResultService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http: HttpClient) { }

  getResultDetails(testId){
    return this.http.get(this.rootUrl+'/api/test/result/'+testId);
  }
  
}
