import { UserService } from './../shared/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private router:Router, private userService:UserService) { }

  ngOnInit() {
  }
  Logout(){
    localStorage.removeItem("userToken");
    this.router.navigate(['/login']);
  }

}
