import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { ExamComponent } from './exam/exam.component';
import { RegisterComponent } from './register/register.component';
import { RegisterSuccessComponent } from './register/register-success/register-success.component';
import { RegisterFailureComponent } from './register/register-failure/register-failure.component';
import { ViewExamComponent } from './exam/view-exam/view-exam.component';
import { ResultExamComponent } from './exam/result-exam/result-exam.component';
import { AddexamComponent } from './exam/addexam/addexam.component';
import { UserManagementComponent } from './admin/user-management/user-management.component';
import { AuthGuard } from './_guards/index';


const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] },
  { path: 'addexam', component: AddexamComponent },
  { path: 'user-managment', component: UserManagementComponent, canActivate: [AuthGuard] },
  { path: 'exam', component: ExamComponent, canActivate: [AuthGuard],
     children : [
        {path: 'view-exam/:examId', component: ViewExamComponent, canActivateChild: [ AuthGuard ]},
        {path: 'result-exam/:examId', component: ResultExamComponent, canActivateChild: [ AuthGuard ]}
      ]
  },
  { path: 'register', component: RegisterComponent,
    children : [
        {path: 'register-success/:registrationId/:email', component: RegisterSuccessComponent},
        {path: 'register-failure', component: RegisterFailureComponent}
     ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
