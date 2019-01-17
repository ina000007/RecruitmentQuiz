import { ToastrService } from 'ngx-toastr';
  import { QuesCategoryService } from './../services/quesCategory/ques-category.service';
    import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

    @Component({
      selector: 'app-upload-question',
      templateUrl: './upload-question.component.html',
      styleUrls: ['./upload-question.component.css']
    })
    export class UploadQuestionComponent implements OnInit {
      public addCat;
      public categories: any[];

      constructor(private quesCategoryService:QuesCategoryService, private toastr: ToastrService) {
        this.getAllCategory();
      }

      ngOnInit() {
      }
      saveCat(val){
                this.quesCategoryService.addCategory(val)
          .subscribe((data:any)=>{
            if(data.success==true){
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
            }
            else{
              this.categories=["No Category, Please add"];
            }
          });
      }
  }
