import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditModalPeopleComponent } from './edit-modal-people.component';

describe('EditModalPeopleComponent', () => {
  let component: EditModalPeopleComponent;
  let fixture: ComponentFixture<EditModalPeopleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditModalPeopleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditModalPeopleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
