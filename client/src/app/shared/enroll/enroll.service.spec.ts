import { TestBed, inject } from '@angular/core/testing';

import { CourseEnrollService } from './enroll.service';

describe('EnrollService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CourseEnrollService]
    });
  });

  it('should be created', inject([CourseEnrollService], (service: CourseEnrollService) => {
    expect(service).toBeTruthy();
  }));
});
