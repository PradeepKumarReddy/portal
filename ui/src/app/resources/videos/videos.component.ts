import { Component, OnInit } from '@angular/core';
import { Subject, SubResource } from '../../_models/index';
import { SubjectService, AlertService } from '../../_services/index';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-videos',
  templateUrl: './videos.component.html',
  styleUrls: ['./videos.component.css']
})
export class VideosComponent implements OnInit {
subResources: SubResource[] = [];
subject: Subject;
subjects: Subject[] = [];
name: string;
type: string;
isLoadResource = false;
viewResource: SubResource;
viewResourceLink: string;
  constructor(private subjectService: SubjectService,
  private alertService: AlertService,
  public sanitizer: DomSanitizer) { }

  ngOnInit() {
  this.name = 'History';
  this.type = 'Video';
  this.getSubjectByNameAndType();
  this.getAllSubjectByType();
  }

  getSubjectByNameAndType() {
  this.subjectService.getSubjectByNameAndType(this.name, this.type).subscribe(
      (sub: Subject) => {
       console.log(sub);
       this.subject = sub;
       this.subResources = [...sub.subResource];
      },
      (err) => {
        console.error(err);
        this.alertService.error('Some thing went wrong, try again');
      },
      () => console.log('subject loaded successful')
  );
  }

  getAllSubjectByType() {
  this.subjectService.getAllSubjectByType(this.type).subscribe(
      (sub: Subject[]) => {
       console.log(sub);
       this.subjects = [...sub];
      },
      (err) => {
        console.error(err);
        this.alertService.error('Some thing went wrong, try again');
      },
      () => console.log('subject loaded successful')
  );
  }

  gotoVideo(res: SubResource) {
  this.isLoadResource = true;
  this.viewResource = res;
  const strLink = this.viewResource.resourceLink;
  this.viewResourceLink = strLink.replace('watch?v=', 'embed/');
  console.log(this.viewResourceLink);
  console.log('gotoVideo link');
  }

}
