import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCoverletterComponent } from './edit-coverletter.component';

describe('EditCoverletterComponent', () => {
  let component: EditCoverletterComponent;
  let fixture: ComponentFixture<EditCoverletterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCoverletterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCoverletterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
