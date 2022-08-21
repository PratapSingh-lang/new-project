import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ToolbarcomponentComponent } from './toolbarcomponent.component';

describe('ToolbarcomponentComponent', () => {
  let component: ToolbarcomponentComponent;
  let fixture: ComponentFixture<ToolbarcomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToolbarcomponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ToolbarcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
