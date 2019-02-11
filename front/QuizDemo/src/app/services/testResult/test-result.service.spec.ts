import { TestBed, inject } from '@angular/core/testing';

import { TestResultService } from './test-result.service';

describe('TestResultService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TestResultService]
    });
  });

  it('should be created', inject([TestResultService], (service: TestResultService) => {
    expect(service).toBeTruthy();
  }));
});
