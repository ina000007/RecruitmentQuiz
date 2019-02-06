import { ActivatedRoute, Router } from '@angular/router';
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
  constructor(private userService: UserService,private router:Router,
     private toastr: ToastrService,private route: ActivatedRoute) {
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
          // this.resetForm(form);
          this.toastr.success("success "+data.message);
          this.userService.userAuthentication(this.user.Username.trim(),this.user.Password.trim()).subscribe((data: any)=>{
            localStorage.setItem("userToken",data.accessToken);
            localStorage.setItem("userRole",data.role.name);
            localStorage.setItem("usernameOrEmail",data.role.name);
            let returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
            this.router.navigate([returnUrl || '/home']);
        });
        } 
        else{
          console.log("User register failed "+data);
          this.toastr.error("Error "+data.message);
        }
      });
  }

}
