import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NgxPaginationModule} from 'ngx-pagination';
import { SimpleTimer } from 'ng2-simple-timer';
import {HttpClientModule} from '@angular/common/http';
// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard, DeactivateGuard } from './_guards/index';
import { AuthenticationService, UserService, AuthService, RegisterService,
    AddQuestionService, ExamService, GlobalService, AddExamService,
    UserManagmentService, ResetPasswordService, AlertService,
    SubjectService, ContactUsService, ReportsService } from './_services/index';
import { HomeComponent } from './home/index';
import { ExamComponent } from './exam/exam.component';
import { TimerComponent } from './timer/timer.component';
import { HeaderComponent } from './header/header.component';
import { NewtimerComponent } from './newtimer/newtimer.component';
import { SecondsToDateTimePipe } from './_helpers/index';
import { RegisterComponent } from './register/register.component';
import { ViewExamComponent } from './exam/view-exam/view-exam.component';
import { ResultExamComponent } from './exam/result-exam/result-exam.component';
import { RegisterSuccessComponent } from './register/register-success/register-success.component';
import { RegisterFailureComponent } from './register/register-failure/register-failure.component';
import { AddexamComponent } from './exam/addexam/addexam.component';
import { UserManagementComponent } from './admin/user-management/user-management.component';
import { EnabledUsersPipe } from './admin/user-management/user-management.pipe';
import { ResetPasswordComponent } from './login/reset-password/reset-password.component';
import { AlertComponent } from './_directives/alert/alert.component';
import { ResourcesComponent } from './resources/resources.component';
import { VideosComponent } from './resources/videos/videos.component';
import { DocumentsComponent } from './resources/documents/documents.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { SampleComponent } from './resources/sample/sample.component';
import { FooterComponent } from './footer/footer.component';
import { CompletedExamsComponent } from './exam/completed-exams/completed-exams.component';
import { AddResourceComponent } from './resources/add-resource/add-resource.component';
import { QuestionsUploadComponent } from './admin/questions-upload/questions-upload.component';
import { MyProfileComponent } from './register/my-profile/my-profile.component';
import { ResetPasswordAdminComponent } from './admin/reset-password-admin/reset-password-admin.component';
import { UserExamReportComponent } from './reports/user-exam-report/user-exam-report.component';
import { ExamReportDetailsComponent } from './reports/exam-report-details/exam-report-details.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AdminComponent,
    ExamComponent,
    TimerComponent,
    HeaderComponent,
    NewtimerComponent,
    SecondsToDateTimePipe,
    RegisterComponent,
    ViewExamComponent,
    ResultExamComponent,
    RegisterSuccessComponent,
    RegisterFailureComponent,
    AddexamComponent,
    UserManagementComponent,
    ResetPasswordComponent,
    AlertComponent,
    ResourcesComponent,
    VideosComponent,
    DocumentsComponent,
    AboutUsComponent,
    SampleComponent,
    FooterComponent,
    CompletedExamsComponent,
    AddResourceComponent,
    QuestionsUploadComponent,
    MyProfileComponent,
    ResetPasswordAdminComponent,
    EnabledUsersPipe,
    UserExamReportComponent,
    ExamReportDetailsComponent,
    ConfirmationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    NgxPaginationModule,
    HttpClientModule,
    ModalModule.forRoot()
  ],
  providers: [
    AuthGuard,
    DeactivateGuard,
    AuthenticationService,
    AuthService,
    UserService,
    RegisterService,
    AddQuestionService,
    ExamService,
    GlobalService,
    AddExamService,
    UserManagmentService,
    ResetPasswordService,
    AlertService,
    SubjectService,
    ContactUsService,
    ReportsService,
    // providers used to create fake backend
    fakeBackendProvider,
    MockBackend,
    BaseRequestOptions,
    SimpleTimer,
    SecondsToDateTimePipe
  ],
  entryComponents: [
    ConfirmationComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
