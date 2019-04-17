import { Constants } from './../../constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class TestService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http: HttpClient) { }

  initialize(emailId,testId){
    return this.http.get(this.rootUrl+'/api/test/initialize/'+testId+"/"+emailId);
  }
  
  saveAnsDetails(body){
    return this.http.post(this.rootUrl+'/api/test/saveans',body);
  }

  resetSaveAnsDetails(body){
    return this.http.post(this.rootUrl+'/api/test/reset',body);
  }

  getTime(emailId,testId){
    return this.http.get(this.rootUrl+'/api/test/time/'+testId+"/"+emailId);
  }

  updateTimer(emailId,testId){
    return this.http.get(this.rootUrl+'/api/test/ping/'+testId+"/"+emailId);
  }
  updateTimeWithTime(time,emailId,testId){
    return this.http.get(this.rootUrl+'/api/test/ping/'+time+'/'+testId+"/"+emailId);
  }
}
