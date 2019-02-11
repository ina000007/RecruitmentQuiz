import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SumbitTestComponent } from './sumbit-test.component';

describe('SumbitTestComponent', () => {
  let component: SumbitTestComponent;
  let fixture: ComponentFixture<SumbitTestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SumbitTestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SumbitTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
