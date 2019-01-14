import { User } from './../shared/user.model';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  user: User;
  constructor() { }

  ngOnInit() {
  }

  resetForm(form: NgForm){
    if(form !=null)
    form.reset();
    this.user={
      Username:'',
      Password:'',
      Email:'',
      Name:''
    };
  }
  OnSubmit(form:NgForm){
    console.log("called onsubmit");
  }

}
