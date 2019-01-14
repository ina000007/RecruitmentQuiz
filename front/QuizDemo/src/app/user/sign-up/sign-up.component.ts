import { User } from '../../shared/user.model';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../../shared/user.service';
import { ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  user: User;
  constructor(private userService: UserService, private toastr: ToastrService) {
   }

  ngOnInit() {
    this.user =new User();
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
    this.userService.registerUser(form.value)
      .subscribe((data:any)=>{
        if(data.success == true){
          console.log("User register"+data);
          this.resetForm(form);
          this.toastr.success("success "+data.message);
        } 
        else{
          console.log("User register failed "+data);
          this.toastr.error("Error "+data.message);
        }
      });
  }

}
