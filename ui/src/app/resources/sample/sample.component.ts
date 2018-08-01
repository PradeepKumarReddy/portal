import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.css']
})
export class SampleComponent implements OnInit {
viewResourceLink = 'https://www.youtube.com/embed/G0EPc42a-6g';
  constructor(public sanitizer: DomSanitizer) { }

  ngOnInit() {
  }

}
