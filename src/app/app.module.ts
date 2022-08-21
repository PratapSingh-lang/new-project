import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './shared/material.module';
import { MyComponentComponent } from './my-component/my-component.component';
import { ToolbarcomponentComponent } from './Toolbar/toolbarcomponent/toolbarcomponent.component';

// import { SelectionModel } from '@angular/cdk/collections';
// import { Component, EventEmitter, Inject, Input, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
// import { FormControl } from '@angular/forms';
// import { MatMenuTrigger } from '@angular/material/menu';
// import { Observable, Subscription } from 'rxjs';
// import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    MyComponentComponent,
    ToolbarcomponentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    // SelectionModel,
    // FormControl,
    FormsModule
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
