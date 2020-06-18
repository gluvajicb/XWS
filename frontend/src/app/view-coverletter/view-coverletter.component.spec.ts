import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCoverletterComponent } from './view-coverletter.component';

describe('ViewCoverletterComponent', () => {
  let component: ViewCoverletterComponent;
  let fixture: ComponentFixture<ViewCoverletterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewCoverletterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCoverletterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
