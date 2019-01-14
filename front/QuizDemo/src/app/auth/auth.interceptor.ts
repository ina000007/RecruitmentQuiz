    import { HttpInterceptor, HttpRequest,HttpHandler, HttpUserEvent, HttpEvent } from '@angular/common/http';
    import { Observable } from "rxjs/Observable";
    import 'rxjs/add/operator/do';
    import { UserService } from '../shared/user.service';
    import { Injectable } from '@angular/core'; 
    import { Router } from '@angular/router';
    @Injectable()
    export class AuthInterceptor implements HttpInterceptor{
        constructor(private router:Router){}
        intercept(req: HttpRequest<any>, next:HttpHandler):Observable<HttpEvent<any>>{
            if(req.headers.get('No-Auth')=="True")
                return next.handle(req.clone());
            if(localStorage.getItem("userToken")!=null){
                const clonedReq = req.clone({
                    headers: req.headers.set("Authorization","Bearer "+localStorage.getItem("userToken"))
                });
                return next.handle(clonedReq)
                .do(
                    succ=>{},
                    err=>{
                        debugger;
                        if(err.status === 401)
                        this.router.navigate(['/login']);
                    }
                );
            }  
            else  {
                this.router.navigate(['/login']);
            }
        }
    }