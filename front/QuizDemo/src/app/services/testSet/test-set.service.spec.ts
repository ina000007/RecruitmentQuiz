import { TestBed, inject } from '@angular/core/testing';

import { TestSetService } from './test-set.service';

describe('TestSetService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TestSetService]
    });
  });

  it('should be created', inject([TestSetService], (service: TestSetService) => {
    expect(service).toBeTruthy();
  }));
});
