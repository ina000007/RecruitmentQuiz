import { UserService } from './../shared/user.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import  { Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router:Router, private userService: UserService){

  }
  canActivate(next: ActivatedRouteSnapshot,state: RouterStateSnapshot): boolean {
    if(localStorage.getItem("userToken")!=null){
      let role = next.data["role"] ;
      if(role){
        var match = this.userService.roleMatch(role);
        if(match) return true;
        else{
          this.router.navigate(['/forbidden']);
          return false;
        }
      }
      else
        return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
