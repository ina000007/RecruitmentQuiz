import { TestResultService } from './../services/testResult/test-result.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-result',
  templateUrl: './test-result.component.html',
  styleUrls: ['./test-result.component.css']
})
export class TestResultComponent implements OnInit {

resultSet;
  constructor(private testResultService:TestResultService) { }

  ngOnInit() {
  }

  getResult(testId){
    this.testResultService.getResultDetails(testId)
    .subscribe((data:any)=>{
      if(data.success==true){
        console.log("success "+ JSON.stringify(data));
        this.resultSet = data.obj.resultSet;
        console.log("here "+ JSON.stringify(this.resultSet));
        
      }
      else{
        console.log("fail "+ JSON.stringify(data));
      }
    });
  }

}
