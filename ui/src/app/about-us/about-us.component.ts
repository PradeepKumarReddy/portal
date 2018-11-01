import { Component, OnInit } from '@angular/core';
import { Contactus } from '../_models/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ContactUsService } from '../_services/index';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private contactUsService: ContactUsService) {
    this.contactUs = {};
  }
  contactUs: Contactus;
  ngOnInit() {
  this.contactUsService.getContactUs().subscribe(
    (res: Contactus) => {
        this.contactUs = res;
        // console.log(this.contactUs);
       },
      err => console.error(err),
      () => console.log('loaded ContactUs successful')
  );
  }
}
