import { TestBed, inject } from '@angular/core/testing';

import { QuesAnsService } from './ques-ans.service';

describe('QuesAnsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QuesAnsService]
    });
  });

  it('should be created', inject([QuesAnsService], (service: QuesAnsService) => {
    expect(service).toBeTruthy();
  }));
});
