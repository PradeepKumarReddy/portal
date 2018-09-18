import { Component, OnInit } from '@angular/core';
import { Subject, SubResource } from '../../_models/index';
import { SubjectService, AlertService } from '../../_services/index';

@Component({
  selector: 'app-add-resource',
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent implements OnInit {
  addSubResources: SubResource;
  subjects: Subject[] = [];
  constructor(private subjectService: SubjectService,
  private alertService: AlertService) { }

  ngOnInit() {
  this.addSubResources = new SubResource();
  this.addSubResources.subject = new Subject();
  this.getAllSubjects();
  }

  getAllSubjects() {
  this.subjectService.getAllSubjects().subscribe(
  (res: Subject[]) => {
  this.subjects = [...res];
  console.log(this.subjects);
  }, (err) => console.log(err),
  () => console.log('subject loaded successful')
  );
  }

  addSubjectToResource(sub) {
  this.addSubResources.subject.name = sub;
  }

  addResource() {
  console.log(this.addSubResources);
  this.subjectService.addSubResource(this.addSubResources).subscribe(
  (res: SubResource) => {
  this.alertService.success('Added SubResource Successfully');
  },
  (err) => {
  this.alertService.error('Some thing went wrong, try again');
  },
  () => {
  console.log('Added SubResource Successfully');
  this.addSubResources = new SubResource();
  this.addSubResources.subject = new Subject();
  }
  );
  }
}
