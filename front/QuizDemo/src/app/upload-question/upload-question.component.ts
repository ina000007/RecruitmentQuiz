import { ToastrService } from 'ngx-toastr';
import { QuesCategoryService } from './../services/quesCategory/ques-category.service';
import { Component, OnInit, ElementRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuesAnsService } from '../services/quesAns/ques-ans.service';
import * as XLSX from  'xlsx';


@Component({
  selector: 'app-upload-question',
  templateUrl: './upload-question.component.html',
  styleUrls: ['./upload-question.component.css']
})
export class UploadQuestionComponent implements OnInit {
	public addCat;
	public categories: any[];
	public selectedLevel;
	public result;
	constructor(private el: ElementRef,private quesCategoryService:QuesCategoryService, private toastr: ToastrService, private quesAnsService:QuesAnsService) {
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
	res={};
	file:any;
  fileName:String;
  sheetName:string;
	fileChanged(e) {
		this.file = e.target.files[0];
    this.fileName = e.target.files[0].name;
    this.fileName = this.fileName.split(".")[0];
	}

	uploadDocument() {
		var data={};
    // this.exportFile();
    if(this.selectedLevel==null)
    	this.toastr.error("Choose Catagory");
    else    if(this.file==null)
    	this.toastr.error("File not selected "+this.selectedLevel);
    else{
    	const target:DataTransfer = (<DataTransfer>(this.el.nativeElement));
    	let fileReader = new FileReader();
    	fileReader.onload = (file:any) => {
    		var wb = XLSX.read(file.target.result,{type:'binary'});

    		wb.SheetNames.forEach((name) => {
                // name =  name.replace(/&nbsp;/g,'') ;
                this.sheetName=name.trim();
                data[name.trim()] = XLSX.utils.sheet_to_json(wb.Sheets[name]);            
            });
    		for(let index=0;index<data[this.sheetName].length;index++){
    			data[this.sheetName][index]['type']="1";  
    		}
    		let temp =JSON.stringify( data[this.sheetName]);
    		var str = temp.replace(new RegExp(String.fromCharCode(160),"g"),"");
    		console.log(" new result "+str );

    		this.quesAnsService.addQues(JSON.parse(str))
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
    	fileReader.readAsBinaryString(this.file);
    }
}
}



