import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GitUrlModalComponent } from './git-url-modal.component';

describe('GitUrlModalComponent', () => {
  let component: GitUrlModalComponent;
  let fixture: ComponentFixture<GitUrlModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GitUrlModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GitUrlModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
