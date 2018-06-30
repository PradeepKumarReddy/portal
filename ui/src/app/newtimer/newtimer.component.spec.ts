import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewtimerComponent } from './newtimer.component';

describe('NewtimerComponent', () => {
  let component: NewtimerComponent;
  let fixture: ComponentFixture<NewtimerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewtimerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewtimerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
