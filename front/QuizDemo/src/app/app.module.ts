  import { AuthInterceptor } from './auth/auth.interceptor';
    import { AuthGuard } from './auth/auth.guard';
    import { appRoutes } from './routes';
    import { BrowserModule } from '@angular/platform-browser';
    import { NgModule } from '@angular/core';

    import{ FormsModule }from '@angular/forms';

    import { AppComponent } from './app.component';
    import { SignUpComponent } from './user/sign-up/sign-up.component';
    import { UserService } from './shared/user.service';

    import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; 

    import{ BrowserAnimationsModule }  from '@angular/platform-browser/animations';
    import {ToastrModule} from 'ngx-toastr';
    import { UserComponent } from './user/user.component';
    import { SignInComponent } from './user/sign-in/sign-in.component';
    import { HomeComponent } from './home/home.component';

    import {RouterModule} from '@angular/router';
  import { AdminPanelComponent } from './admin-panel/admin-panel.component';
  import { ForbiddenComponent } from './forbidden/forbidden.component';

    @NgModule({
      declarations: [
        AppComponent,
        SignUpComponent,
        UserComponent,
        SignInComponent,
        HomeComponent,
        AdminPanelComponent,
        ForbiddenComponent
      ],
      imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        BrowserAnimationsModule,
        RouterModule.forRoot(appRoutes)
      ],
      providers: [UserService,
      AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi:true
    }],
      bootstrap: [AppComponent]
    })
    export class AppModule { }
