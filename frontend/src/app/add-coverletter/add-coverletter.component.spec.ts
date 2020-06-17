import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCoverletterComponent } from './add-coverletter.component';

describe('AddCoverletterComponent', () => {
  let component: AddCoverletterComponent;
  let fixture: ComponentFixture<AddCoverletterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCoverletterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCoverletterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
