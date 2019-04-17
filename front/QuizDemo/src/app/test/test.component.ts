import { TestService } from './../services/test/test.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  newClock;
  clock;
  testStart=false;
  testId;
  emailId;
  quesLst;
  ques;
  selectedPage;
  dis=true;
  crrRef;
  preRef;
  selectedOptionVal;
  interval;
  timeUpdt;
  newClockFormat(clock){
    this.newClock = new Date(clock * 1000).toISOString().substr(11, 8);
  }
  countDown(){
    this.interval = setInterval(() => {
      if(this.clock>0){
      this.clock--;
      this.newClockFormat(this.clock);
    }
      else{
        console.log("here in counter "+this.clock);
        clearInterval(this.interval);
        if(this.clock==0)
        this.router.navigate(["/submitTest"]);
      }
    }
    , 1000);
  }
  updateTime(){
    this.timeUpdt = setInterval(() => {
      if(this.clock>0){
        this.testService.updateTimer(this.emailId,this.testId)
        .subscribe((data:any)=>{
          console.log("time updated "+this.clock+"  "+this.newClock);
          
        });
      }
      else{
        clearInterval(this.timeUpdt);
      }
    }
    , 60000);
  }
  constructor(private router:Router, private testService:TestService) {
    let cUrl=router.url;
    this.testId = cUrl.substring(cUrl.lastIndexOf("/")+1);
    this.emailId = localStorage.getItem("emailId");
    this.initializeTest();
    this.quesLst=[];
    this.countDown();
    this.intiClock();
    
  }
  ngOnDestroy() {
    this.testService.updateTimeWithTime(this.clock,this.emailId,this.testId)
    .subscribe((data:any)=>{
      console.log("time updated final only once "+this.clock);
      this.clock=-1;
      console.log("view");
    });

  }
  ngOnInit() {
  }
  initializeTest(){
    
    this.testService.initialize(this.emailId,this.testId)
    .subscribe((data:any)=>{
      if(data.success==true){
        console.log("123 success "+JSON.stringify(data));
        this.quesLst =  data.obj;
        console.log(this.quesLst);
        this.selectedPage=0;
        this.ques=this.quesLst[0];
        this.testStart = true;
        this.updateTime();

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

  intiClock(){
    this.testService.getTime(this.emailId,this.testId)
    .subscribe((data:any)=>{
      if(data!=null){
        console.log("22 success "+JSON.stringify(data));
        this.clock = data.time;
      }
      else{
        console.log("fail "+JSON.stringify(data));
      }
    });
  }
  
  showQues(index,ref){
    // this.ques=this.quesLst[index];
    // ref.className = 'active'
    if(this.preRef!=null)
    this.preRef.classList.remove('active');
    ref.classList.add('active');
    this.preRef=ref;
    this.selectedPage = index;
    this.ques=this.quesLst[index];
  }
  // show(indx,ref){
  //   // alert("here");
  //   let index = this.selectedPage + indx;
  //   console.log("index "+index);
  
  //   if(index>=0 && index<this.quesLst.length)
  //       this.showQues(index,ref);
  // }
  selectedOption(event:any){
    this.selectedOptionVal = event.target.value;
    this.saveAns(this.ques);
  }
  saveAns(ques){
    console.log("called saveAns service");
    
    let body={
      "emailId":this.emailId,
      "testId":this.testId,
      "quesId":ques.id,
      "selectedOptionVal":this.selectedOptionVal
    };
    
    this.testService.saveAnsDetails(body)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.ques.selectedOpt = this.selectedOptionVal;
        this.quesLst[this.selectedPage]=this.ques;
      }
      else{
        
      }
    });
  }
  
  resetSaveAns(q){
    console.log("called Reset service");
    
    let body={
      "emailId":this.emailId,
      "testId":this.testId,
      "quesId":q.id,
      "selectedOptionVal": ""
    };
    
    this.testService.resetSaveAnsDetails(body)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.ques= data.obj;
        this.quesLst[this.selectedPage]=this.ques;
      }
      else{
        
      }
    });
  }
  
}
