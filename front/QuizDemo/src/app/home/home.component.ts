import { TestSetService } from './../services/testSet/test-set.service';
import { UserService } from './../shared/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  clgName;
  

  constructor(private router:Router, private toastr: ToastrService, private userService:UserService,private testSetService:TestSetService) {
    
  }
  getTestSetDetail(testId){
    this.testSetService.getTestSet(testId)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.clgName = data.obj.collegeDetail.collegeId.clgName;
        this.router.navigate(["/test/"+this.clgName+"/"+testId]);
      }
      else{
        this.toastr.error("Error "+data.message);
      }
    });
  }
  ngOnInit() {
  }


}
