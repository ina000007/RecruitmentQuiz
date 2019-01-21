import { ToastrService } from 'ngx-toastr';
import { QuesCategoryService } from './../services/quesCategory/ques-category.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuesAnsService } from '../services/quesAns/ques-ans.service';

@Component({
  selector: 'app-upload-question',
  templateUrl: './upload-question.component.html',
  styleUrls: ['./upload-question.component.css']
})
export class UploadQuestionComponent implements OnInit {
  public addCat;
  public categories: any[];
  selectedLevel;

  constructor(private quesCategoryService:QuesCategoryService, private toastr: ToastrService, private quesAnsService:QuesAnsService) {
    this.getAllCategory();
  }

  ngOnInit() {
  }
  saveCat(val){
    this.quesCategoryService.addCategory(val)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.selectedLevel=val;
        this.toastr.success("success "+data.message);
        this.getAllCategory();        
      }
      else{
        this.toastr.error("Error "+data.message);
      }
    });
  }
  getAllCategory(){
    console.log("called quesCategoryService");
    this.quesCategoryService.getCategory()
    .subscribe((data:any)=>{
      if(data.length>0){
        this.categories = data;
        this.selectedLevel=this.categories[-1];
      }
      else{
        this.categories=["No Category, Please add"];
      }
    });
  }
  file:any;
  fileChanged(e) {
    this.file = e.target.files[0];
  }
  uploadDocument() {
    if(this.selectedLevel==null)
    this.toastr.error("Choose Catagory");
    else    if(this.file==null)
    this.toastr.error("File not selected "+this.selectedLevel);
    else{
      let fileReader = new FileReader();
      fileReader.onload = (e) => {
        console.log(fileReader.result);
        console.log(this.csvToJson(fileReader.result));
        
        this.quesAnsService.addQues(this.csvToJson(fileReader.result))
        .subscribe((data:any)=>{
          var data1=data;
        var success=0;
        var duplicate=0;
        var fail=0;
        var dupQues="";
        for(var i=0;i<data.length;i++){
          var obj=data[i];
          if(obj.success==true)
            success=success+1;
          else if(obj.success==false){
            duplicate=duplicate+1;
            dupQues = dupQues+"-> "+obj.quesDesc+"\n";
            }
          else 
            fail=fail+1;				
        }
        var temp = (duplicate>0)?"\n\n\n\n\n\n\n\nThese are already added:\n"+dupQues:"";
        console.log("Added: "+success+" Question\n"+"failed: "+fail+" Question\n"+"Duplicate: "+duplicate+" Question\n"+temp);      
        this.toastr.info("Added: "+success+" Question\n"+"failed: "+fail+" Question\n"+"Duplicate: "+duplicate+" Question\n"+temp);
        this.toastr.show(temp);  
      });
      }
      fileReader.readAsText(this.file);
    }
  }
  
  csvToJson(csv){
    csv = csv.replace(/\r/g, "");
    var lines = csv.split("\n");
    var result = [];
    lines[0] = "questionDesc,option1,option2,option3,option4,answer,mark,type";
    var headers = lines[0].split(",");
    for (var i = 1; i < lines.length; i++) {
        var obj = {};
        if(lines[i].split(",").length==6)
          lines[i]=lines[i]+",1";
        lines[i]=lines[i]+","+this.selectedLevel;  
        var currentline = lines[i].split(",");
        for (var j = 0; j < headers.length; j++) {
            obj[headers[j]] = currentline[j];
        }
        result.push(obj);
    }
    return result; //JavaScript object
    // return JSON.stringify(result); //JSON
  }
}
