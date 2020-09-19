import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePickerPeopleComponent } from './date-picker-people.component';

describe('DatePickerPeopleComponent', () => {
  let component: DatePickerPeopleComponent;
  let fixture: ComponentFixture<DatePickerPeopleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatePickerPeopleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatePickerPeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
