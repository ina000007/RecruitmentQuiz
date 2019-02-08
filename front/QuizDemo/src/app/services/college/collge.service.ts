import { Constants } from './../../constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CollgeService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http: HttpClient) { }

  addClg(body){
    return this.http.post(this.rootUrl+'/api/testset/addcollege',body);
  }
  getAllClg(){
    return this.http.get(this.rootUrl+'/api/college/getcollege');
  }
  
}
