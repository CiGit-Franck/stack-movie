import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { MovieSheetComponent } from './movie-sheet.component';

describe('MovieSheetComponent', () => {
  let component: MovieSheetComponent;
  let fixture: ComponentFixture<MovieSheetComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieSheetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieSheetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
