import { ToastrService } from 'ngx-toastr';
import { CollgeService } from './../services/college/collge.service';
import { NgForm } from '@angular/forms';
import { CollegeId } from './../services/college/college-id.model';
import { College } from './../services/college/college.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-college',
  templateUrl: './add-college.component.html',
  styleUrls: ['./add-college.component.css']
})
export class AddCollegeComponent implements OnInit {
college : College;
collegeId: CollegeId;
  constructor(private clgService:CollgeService, private toastr: ToastrService) { }

  ngOnInit() {
    this.college = new College();
    this.collegeId= new CollegeId();
  }
  OnSubmit(form:NgForm){
    let FrmData = new College();
    FrmData.collegeId = this.collegeId;
    FrmData.clgUniversity = this.college.clgUniversity;
    FrmData.clgState = this.college.clgState;
    console.log("called onsubmit college "+form.value);
    this.clgService.addClg(FrmData)
      .subscribe((data:any)=>{
        if(data.success == true){
          console.log("College register"+data);
          this.resetForm(form);
          this.toastr.success("success "+data.message);
        } 
        else{
          console.log("College register failed "+data);
          this.toastr.error("Error "+data.message);
        }
      });
  }

  resetForm(form: NgForm){
    if(form !=null)
    form.reset();
    this.college=new College();
    this.collegeId= new CollegeId();
  }
}
