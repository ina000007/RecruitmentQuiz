import { ToastrService } from 'ngx-toastr';
import { QuesAnsService } from './../services/quesAns/ques-ans.service';
import { QuesCategoryService } from './../services/quesCategory/ques-category.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit {

public categories;
public selectedLevel;
public quesLst;
  // @Input() upldQuesCom: UploadQuestionComponent;
  constructor(private  quesCategoryService:QuesCategoryService,private toastr: ToastrService, private quesAnsService:QuesAnsService) {
    this.getAllCategory();
   }

  ngOnInit() {
    
  }

  getAllCategory(){
    console.log("called quesCategoryService");
    this.quesCategoryService.getCategory()
    .subscribe((data:any)=>{
      if(data.length>0){
        data.unshift({'id':0,'questionCategory':'--ALL--'});
        this.categories = data;
        this.selectedLevel=this.categories[0].id;
        console.log(this.categories);
        this.getQuesByCat(this.selectedLevel);
      }
      else{
        this.categories=["No Category, Please add"];
      }
    });
  }
  getQuesByCat(id){
    console.log("caleed");
    this.quesAnsService.getQuesByCatId(id)
    .subscribe((data:any)=>{
      if(data.length>0){
        console.log(data);
        this.quesLst=data;
      }
      else{
        console.log(data);
      }
    });
  }
  updateQues(ques,desc,opt1,opt2,opt3,opt4,ans,mark){
    ques.questionDesc=desc;
    ques.option1=opt1;
    ques.option2=opt2;
    ques.option3=opt3;
    ques.option4=opt4;
    ques.answer=ans;
    ques.mark=mark;
    this.quesAnsService.updtQues(ques)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.toastr.success("success "+data.message);
      }
      else{
        this.toastr.error("Failed "+data.message);
      }
    });
  }
  deleteQues(ques){
   let index: number = this.quesLst.indexOf(ques);
   this.quesLst.splice(index, 1);
    this.quesAnsService.delQues(ques.id)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.toastr.success("success "+data.message);
      }
      else{
        this.toastr.error("Failed "+data.message);
      }
    });
  }
}
