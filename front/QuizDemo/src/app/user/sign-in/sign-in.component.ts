import { UserService } from './../../shared/user.service';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  isLoginError: boolean = false;
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
  }
  onSubmit(usernameOrEmail,password){
    usernameOrEmail = String(usernameOrEmail).trim();
    password = String(password).trim();
    this.userService.userAuthentication(usernameOrEmail,password).subscribe((data: any)=>{
        localStorage.setItem("userToken",data.accessToken);
        localStorage.setItem("userRole",data.role.name);
        this.router.navigate(['/home']);
    },
  (err : HttpErrorResponse)=>{
     this.isLoginError=true;
  });
  }
}
