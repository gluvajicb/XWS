import { TestBed } from '@angular/core/testing';

import { CoverletterService } from './coverletter.service';

describe('CoverletterService', () => {
  let service: CoverletterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoverletterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
