import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardDirectorComponent } from './dashboard-director.component';

describe('DashboardDirector', () => {
  let component: DashboardDirectorComponent;
  let fixture: ComponentFixture<DashboardDirectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardDirectorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DashboardDirectorComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
