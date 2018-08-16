import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  viewResourceLink = 'https://www.youtube.com/embed/vKrvNMXUto8';
  constructor(public sanitizer: DomSanitizer) { }

  ngOnInit() {
  }

}
