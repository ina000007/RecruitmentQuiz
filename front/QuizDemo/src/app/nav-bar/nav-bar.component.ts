import { UserService } from './../shared/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  isLoggedIn;
  constructor(private router:Router, private userService:UserService) { 
    this.isLoggedIn= localStorage.getItem("isLoggedIn");
  }

  ngOnInit() {
  }
  Logout(){
    localStorage.clear();
    // localStorage.removeItem("userToken");
    // localStorage.removeItem("isLoggedIn");
    // localStorage.removeItem("emailId");
    // localStorage.removeItem("userRole");
    // localStorage.removeItem("emailId");
    this.router.navigate(['/login']);
  }

}
