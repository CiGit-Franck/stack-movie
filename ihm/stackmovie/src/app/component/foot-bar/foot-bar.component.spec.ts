import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { FootBarComponent } from './foot-bar.component';

describe('FootBarComponent', () => {
  let component: FootBarComponent;
  let fixture: ComponentFixture<FootBarComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ FootBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FootBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
