import { ToastrService } from 'ngx-toastr';
import { QuesAnsService } from './../services/quesAns/ques-ans.service';
import { TestSetService } from './../services/testSet/test-set.service';
import { QuesCategoryService } from './../services/quesCategory/ques-category.service';
import { NgForm,FormBuilder,FormGroup,FormArray } from '@angular/forms';
import { CollgeService } from './../services/college/collge.service';
import { College } from './../services/college/college.model';
import { TestSet } from './../services/testSet/test-set.model';
import { Component, OnInit } from '@angular/core';
// declare var $:any;

@Component({
  selector: 'app-create-test',
  templateUrl: './create-test.component.html',
  styleUrls: ['./create-test.component.css']
})
export class CreateTestComponent implements OnInit {
  formGroup: FormGroup;
  totalQuesCat:number;
  testSet: TestSet;
  selectedClg;
  clsLst: College;
  createdTestId="";
  public quesCntLst: any[];
  public categories: any[];
  constructor(private testSetService:TestSetService,private clgService:CollgeService,
     private _fb:FormBuilder,private QuesCategoryService:QuesCategoryService,private quesAnsService:QuesAnsService,
     private toastr: ToastrService) {
    this.getClgLst();
   }

  ngOnInit() {
    this.formGroup= this._fb.group({
      quesCat: this._fb.array([this.initQuesCat()]),
    });
    this.getAllCategory();
  }
initQuesCat(){
  return this._fb.group({
    category:[''],
    queaCnt:['']
  });
}
addMoreques(){
  const control = <FormArray>this.formGroup.controls['quesCat'];
  control.push(this.initQuesCat());
}
delQuesCat(index:number){
  const control = <FormArray>this.formGroup.controls['quesCat'];
  if(control!=null){
    this.totalQuesCat=control.value.length;
  }
  if(this.totalQuesCat>1){
    control.removeAt(index);
  }
  else{
    this.toastr.error("One cat is mandatory");
    return false;
  }
}
  getClgLst(){
    this.clgService.getAllClg()
    .subscribe((data:any)=>{
      if(data.success==true){
        this.clsLst = data.obj;
      }
      else{
        this.clsLst.collegeId.clgName="No College Added yet, Please add";
      }
    });
  }
  map_to_obj( aMap) {
    const obj = {};
    aMap.forEach ((v,k) => { obj[k] = v });
    return obj;
}
  OnSubmit(form:NgForm){
    const control = <FormArray>this.formGroup.controls['quesCat'];
    let totalCate = control.value.length;
    var reqQuesCatNCnt= new Map();
    let totalQues:number;
    totalQues=0;
    for (let index = 0; index < totalCate; index++) {
      let category = form.value['cateSelector_'+index];
      let quesCnt = form.value['quesCnt_'+index];
      reqQuesCatNCnt.set(category+"",quesCnt);
      totalQues = totalQues+parseInt(quesCnt);
    }
    let body ={
      'clgRgsCd':this.selectedClg.clgRgstCd,
      'driveDate':form.value['driveDate'],
      'totalQues':totalQues,
      'allocatedTime':form.value['allocatedTime'],
      'maxMarks':totalQues,
      'strtTime': form.value['strtTime'],
      'endTime': form.value['endTime'],
      'reqQuesCatNCnt':this.map_to_obj(reqQuesCatNCnt)
    };
    
    this.testSetService.createTestSet(body)
    .subscribe((data:any)=>{
      if(data.success==true){
        this.toastr.success("Test Create Successfully, TestID: "+data.obj.id);
        console.log(JSON.stringify(this.selectedClg.clgRgstCd));
        
        this.createdTestId='http://localhost:4200/quiz/'+this.selectedClg.collegeId.clgName+"/"+data.obj.id;
        console.log("success"+JSON.stringify(data));
      }
      else{
        console.log("error"+JSON.stringify(data));
        
      }
    });
  }

  modifyCategoryObj(cateData){

    this.quesAnsService.getQuestCnt()
    .subscribe((result:any)=>{
      this.quesCntLst = result;

          for (let index = 0; index < cateData.length; index++) {
            cateData[index].count = this.quesCntLst[cateData[index].questionCategory];
           }
    this.categories = cateData;
    });
  }

  getAllCategory(){
		this.QuesCategoryService.getCategory()
		.subscribe((data:any)=>{
			if(data.length>0){
        this.categories = data;
        this.modifyCategoryObj(data);
				// this.selectedLevel=this.categories[-1];
			}
			else{
				this.categories=["No Category, Please add"];
			}
    });

    
	}
}
