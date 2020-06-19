import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewedArticlesComponent } from './reviewed-articles.component';

describe('ReviewedArticlesComponent', () => {
  let component: ReviewedArticlesComponent;
  let fixture: ComponentFixture<ReviewedArticlesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewedArticlesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewedArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
