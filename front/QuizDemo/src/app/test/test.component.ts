import { TestService } from './../services/test/test.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  testId;
  emailId;
  quesLst;
  ques;
  selectedPage;
  crrRef;
  preRef;
  
  constructor(private router:Router, private testService:TestService) {
    let cUrl=router.url;
    this.testId = cUrl.substring(cUrl.lastIndexOf("/")+1);
    this.emailId = localStorage.getItem("emailId");
    this.initializeTest();
   }

  ngOnInit() {
  }
  initializeTest(){

    this.testService.initialize(this.emailId,this.testId)
    .subscribe((data:any)=>{
      if(data.success==true){
        console.log("success "+JSON.stringify(data));
        this.quesLst =  data.obj;
        console.log(this.quesLst);
        this.selectedPage=0;
        this.ques=this.quesLst[0];
        // for (let indx = 0; indx < this.quesLst.length; indx++) {
        //   this.isActive[indx]=false;
        // }
        // this.isActive[0]=true;
      }
      else{
        console.log("fail "+JSON.stringify(data));
      }
    });
  }
  showQues(index,ref){
    this.ques=this.quesLst[index];
    console.log("here "+JSON.stringify(ref));
    // ref.className = 'active'
    if(this.preRef!=null)
    this.preRef.classList.remove('active');
    ref.classList.add('active');
    this.preRef=ref;
  }

}
