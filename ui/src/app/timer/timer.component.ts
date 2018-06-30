import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { SimpleTimer } from 'ng2-simple-timer';


@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent implements OnInit {

   counter=300;
   timerId: string;
   timerbutton = 'Subscribe';

   constructor(private st : SimpleTimer, private router: Router) { }

   ngOnInit() {
      this.st.newTimer('1sec', 1);
      this.subscribeTimer();
   }

   subscribeTimer() {
    if (this.timerId) {
      // Unsubscribe if timer Id is defined
      this.st.unsubscribe(this.timerId);
      this.timerId = undefined;
      this.timerbutton = 'Subscribe';
      console.log('timer 0 Unsubscribed.');
    } else {
      // Subscribe if timer Id is undefined
      this.timerId = this.st.subscribe('1sec', () => this.timercallback());
      this.timerbutton = 'Unsubscribe';
      console.log('timer 0 Subscribed.');
    }
    console.log(this.st.getSubscription());
  }

  timercallback(): void {
    this.counter--;
    if(this.counter <=0 ) {
      this.st.unsubscribe(this.timerId);
      this.router.navigate(['/']);
    }
  }

}
