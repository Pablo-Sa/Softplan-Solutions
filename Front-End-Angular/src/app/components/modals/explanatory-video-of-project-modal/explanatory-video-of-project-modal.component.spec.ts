import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplanatoryVideoOfProjectModalComponent } from './explanatory-video-of-project-modal.component';

describe('ExplanatoryVideoOfProjectModalComponent', () => {
  let component: ExplanatoryVideoOfProjectModalComponent;
  let fixture: ComponentFixture<ExplanatoryVideoOfProjectModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExplanatoryVideoOfProjectModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExplanatoryVideoOfProjectModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
