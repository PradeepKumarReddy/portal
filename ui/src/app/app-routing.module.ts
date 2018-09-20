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
import { ResetPasswordComponent } from './login/reset-password/reset-password.component';
import { ResourcesComponent } from './resources/resources.component';
import { VideosComponent } from './resources/videos/videos.component';
import { DocumentsComponent } from './resources/documents/documents.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { SampleComponent } from './resources/sample/sample.component';
import { CompletedExamsComponent } from './exam/completed-exams/completed-exams.component';
import { AddResourceComponent } from './resources/add-resource/add-resource.component';
import { QuestionsUploadComponent } from './admin/questions-upload/questions-upload.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'resetPassword', component: ResetPasswordComponent },
  { path: 'reset', component: ResetPasswordComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] },
  { path: 'resources', component: ResourcesComponent },
  { path: 'sample', component: SampleComponent },
  { path: 'videos', component: VideosComponent, canActivate: [AuthGuard] },
  { path: 'documents', component: DocumentsComponent, canActivate: [AuthGuard] },
  { path: 'addexam', component: AddexamComponent },
  { path: 'contactUs', component: AboutUsComponent },
  { path: 'user-managment', component: UserManagementComponent, canActivate: [AuthGuard] },
  { path: 'add-resource', component: AddResourceComponent, canActivate: [AuthGuard] },
  { path: 'exam', component: ExamComponent, canActivate: [AuthGuard],
     children : [
        {path: 'view-exam/:examId', component: ViewExamComponent, canActivateChild: [ AuthGuard ]}
      ]
  },
  { path: 'completed-exams', component: CompletedExamsComponent, canActivate: [AuthGuard] },
  { path: 'questions-upload', component: QuestionsUploadComponent, canActivate: [AuthGuard] },
  {path: 'result-exam/:userExamId/:username', component: ResultExamComponent, canActivateChild: [ AuthGuard ]},
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
