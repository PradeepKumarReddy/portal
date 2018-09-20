import { Component, OnInit } from '@angular/core';
import { AddQuestionService, AlertService } from '../../_services/index';

@Component({
  selector: 'app-questions-upload',
  templateUrl: './questions-upload.component.html',
  styleUrls: ['./questions-upload.component.css']
})
export class QuestionsUploadComponent implements OnInit {
  selectedFiles: FileList;
  currentFileUpload: File;
  constructor(private questionService: AddQuestionService,
  private alertService: AlertService) { }
  ngOnInit() {
  }
  selectFile(event) {
  this.selectedFiles = event.target.files;
  }
  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.questionService.uploadQuestions(this.currentFileUpload).subscribe(
    (res: any) => {
    if (res.success) {
    this.alertService.success(res.message);
    } else {
    this.alertService.error(res.message);
    }
    },
    (err) => {
    this.alertService.error('Some thing went wrong, try again');
    },
    () => {
    console.log('Questions file uploded successfully');
    }
    );
    this.selectedFiles = undefined;
  }

}
