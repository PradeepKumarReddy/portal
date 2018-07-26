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
import { AuthGuard } from './_guards/index';
import { AuthenticationService, UserService, AuthService, RegisterService,
    AddQuestionService, ExamService, GlobalService, AddExamService } from './_services/index';
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
    AddexamComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    NgxPaginationModule,
    HttpClientModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    AuthService,
    UserService,
    RegisterService,
    AddQuestionService,
    ExamService,
    GlobalService,
    AddExamService,
    // providers used to create fake backend
    fakeBackendProvider,
    MockBackend,
    BaseRequestOptions,
    SimpleTimer,
    SecondsToDateTimePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
