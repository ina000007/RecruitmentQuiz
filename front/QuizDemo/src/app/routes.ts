import { CreateTestComponent } from './create-test/create-test.component';
import { AddCollegeComponent } from './add-college/add-college.component';
import { UpdateQuestionComponent } from './update-question/update-question.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { AuthGuard } from './auth/auth.guard';
import { SignInComponent } from './user/sign-in/sign-in.component';
import { HomeComponent } from './home/home.component';
import {Routes} from '@angular/router';
import { SignUpComponent } from './user/sign-up/sign-up.component';
import { UserComponent } from './user/user.component';
import { UploadQuestionComponent } from './upload-question/upload-question.component';


export const appRoutes: Routes=[
    {path:'home', component:HomeComponent, canActivate:[AuthGuard]},
    {path:'forbidden', component:ForbiddenComponent, canActivate:[AuthGuard]},
    {path:'adminPanel', component:AdminPanelComponent, canActivate:[AuthGuard], data: {role:['ROLE_ADMIN']}},
    {path:'uploadQuestion', component:UploadQuestionComponent, canActivate:[AuthGuard], data: {role:['ROLE_ADMIN']}},
    {path:'updateQuestion', component: UpdateQuestionComponent, canActivate:[AuthGuard], data: {role:['ROLE_ADMIN']}},
    {path:'addCollege', component: AddCollegeComponent, canActivate:[AuthGuard], data: {role:['ROLE_ADMIN']}},
    {path:'createTest', component: CreateTestComponent, canActivate:[AuthGuard], data: {role:['ROLE_ADMIN']}},
    {
    path:'signup', component:UserComponent,
    children:[{path:'',component:SignUpComponent}]
    },
    {
        path:'login', component:UserComponent,
        children:[{path:'',component:SignInComponent}]
    },
    {path:'', redirectTo:'/login', pathMatch:'full'}
] ;