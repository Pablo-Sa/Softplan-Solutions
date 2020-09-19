import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExclusionModalPeopleComponent } from './exclusion-modal-people.component';

describe('ExclusionModalPeopleComponent', () => {
  let component: ExclusionModalPeopleComponent;
  let fixture: ComponentFixture<ExclusionModalPeopleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExclusionModalPeopleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExclusionModalPeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
