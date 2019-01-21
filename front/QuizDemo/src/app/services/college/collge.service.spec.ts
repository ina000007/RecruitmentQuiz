import { TestBed, inject } from '@angular/core/testing';

import { CollgeService } from './collge.service';

describe('CollgeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CollgeService]
    });
  });

  it('should be created', inject([CollgeService], (service: CollgeService) => {
    expect(service).toBeTruthy();
  }));
});
