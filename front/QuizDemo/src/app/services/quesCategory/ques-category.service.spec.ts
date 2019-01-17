import { TestBed, inject } from '@angular/core/testing';

import { QuesCategoryService } from './ques-category.service';

describe('QuesCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QuesCategoryService]
    });
  });

  it('should be created', inject([QuesCategoryService], (service: QuesCategoryService) => {
    expect(service).toBeTruthy();
  }));
});
