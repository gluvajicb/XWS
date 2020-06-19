import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitingArticlesComponent } from './waiting-articles.component';

describe('WaitingArticlesComponent', () => {
  let component: WaitingArticlesComponent;
  let fixture: ComponentFixture<WaitingArticlesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WaitingArticlesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WaitingArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
