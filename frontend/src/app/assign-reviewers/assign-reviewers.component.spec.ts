import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignReviewersComponent } from './assign-reviewers.component';

describe('AssignReviewersComponent', () => {
  let component: AssignReviewersComponent;
  let fixture: ComponentFixture<AssignReviewersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssignReviewersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignReviewersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
