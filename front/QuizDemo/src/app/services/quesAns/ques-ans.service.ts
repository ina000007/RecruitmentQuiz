import { Constants } from './../../constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuesAnsService {

  readonly rootUrl = Constants.SERVICE_URL;
  constructor(private http: HttpClient) { }

  addQues(body){
    return this.http.post(this.rootUrl+'/api/questionanswer/addquestion',body);
  }
  getQuesByCatId(id){
    return this.http.get(this.rootUrl+'/api/questionanswer/question/cat/'+id);
  }
  updtQues(ques){
    return this.http.put(this.rootUrl+'/api/questionanswer/question',ques);
  }
  delQues(quesId){
    return this.http.delete(this.rootUrl+'/api/questionanswer/question/'+quesId);
  }
  getQuestCnt(){
    return this.http.post(this.rootUrl+'/api/questionanswer/quescnt',"");
  }
}
