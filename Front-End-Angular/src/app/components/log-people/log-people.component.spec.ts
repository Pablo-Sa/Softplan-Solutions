import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LogPeopleComponent } from './log-people.component';

describe('LogPeopleComponent', () => {
  let component: LogPeopleComponent;
  let fixture: ComponentFixture<LogPeopleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LogPeopleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LogPeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
