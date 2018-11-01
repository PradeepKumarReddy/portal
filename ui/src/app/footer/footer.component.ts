import { Component, OnInit } from '@angular/core';
import { Contactus } from '../_models/index';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ContactUsService } from '../_services/index';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

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
