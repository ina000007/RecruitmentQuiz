import { AppComponent } from './../../app.component';
import { UserService } from './../../shared/user.service';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  isLoginError: boolean = false;
   
  constructor(private userService:UserService, private router:Router,private route: ActivatedRoute,
  private appComponent:AppComponent) { 

    localStorage.clear();

  }

  ngOnInit() {
  }
  onSubmit(usernameOrEmail,password){
    usernameOrEmail = String(usernameOrEmail).trim();
    password = String(password).trim();
    this.appComponent.showLoadingIndicator=true;
    this.userService.userAuthentication(usernameOrEmail,password).subscribe((data: any)=>{
      this.appComponent.showLoadingIndicator=false;
        localStorage.setItem("userToken",data.accessToken);
        localStorage.setItem("userRole",data.role.name);
        localStorage.setItem("isLoggedIn","true");
        localStorage.setItem("emailId",usernameOrEmail);
        let returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
        this.router.navigate([returnUrl || '/home']);
    },
  (err : HttpErrorResponse)=>{
    this.appComponent.showLoadingIndicator=false;
     this.isLoginError=true;
  });
  }
}
