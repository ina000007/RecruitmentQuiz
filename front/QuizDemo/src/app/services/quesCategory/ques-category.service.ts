import { Constants } from './../../constants';
import { QuesCategory } from './ques-category.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuesCategoryService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http:HttpClient) {
   }

   addCategory(catName){
    const body={
      "questionCategory": catName
    }
    return this.http.post(this.rootUrl+'/api/questionanswer/addcategory',body );
   }
   getCategory(){
    const body={
    }
    return this.http.post(this.rootUrl+'/api/questionanswer/getcategory',body );
   }
}
