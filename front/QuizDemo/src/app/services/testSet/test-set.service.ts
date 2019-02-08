import { Constants } from './../../constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestSetService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http: HttpClient) { }
  
  createTestSet(body){
    return this.http.post(this.rootUrl+'/api/testset/createtest ',body);
  }

  getTestSet(id){
    return this.http.get(this.rootUrl+'/api/testset/testsetdetail/'+id);
  }
}
