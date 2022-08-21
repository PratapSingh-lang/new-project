import { Component, OnInit } from '@angular/core';
import { Inject} from '@angular/core';
import { DateModel } from '../shared/DateModel';
import {
  MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import 'moment/locale/en-ie';
import 'moment/locale/es';
// import { Console } from 'console';
@Component({
  selector: 'app-my-component',
  templateUrl: './my-component.component.html',
  styleUrls: ['./my-component.component.css'],
  providers: [
    
    {provide: MAT_DATE_LOCALE, useValue: 'en'},

    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})
export class MyComponentComponent implements OnInit {
  dateTimeErrMsg: string;
  showAbsoluteDate: Date;
  dateTime: Date;
  startTime: string;
  showDateErrorMessage: boolean;
  // dateModel!: DateModel ;
// dateModel = {};
//  showAbsoluteDate: Date;
  constructor(
    private _adapter: DateAdapter<any>,
    @Inject(MAT_DATE_LOCALE) private _locale: string,
    
  ) { }
  dateModel: DateModel;
  ngOnInit(): void {
    this.dateModel.type = 1;
    this.dateModel.dateTime = new Date();
    this.dateModel.startTime = "02:55:22";
    this.dateModel.timePeriod = 56;
    this.dateModel.dataType = "Timestamp";
  }

  //   this._locale = 'es';
  //   this._adapter.setLocale(this._locale);
  // }

  // applyStartDate(){
  //   if(!this.dateModel.dateTime){
  //     this.showDateErrorMessage = true;
  //     this.dateTimeErrMsg = 'Please select the date ';
  //   }else if (!this.dateModel.startTime && this.dateModel.dataType !== 'date') {
  //     this.showDateErrorMessage = true;
  //     this.dateTimeErrMsg = 'Please enter the time ';
  //   } else if (
  //     this.dateModel.startTime &&
  //     this.dateModel.startTime.length == 0 &&
  //     this.dateModel.dataType !== 'date'
  //   ) {
  //     // this.showDateErrorMessage = true;
  //     this.dateTimeErrMsg = 'Please enter the time ';
  // }else{
  //     // this.showDateErrorMessage = false;
  //       this.dateTimeErrMsg = ''
  //       if(this.dateModel.dataType == 'date' ){
  //       let d = new Date(this.dateModel.dateTime);
  //       this.showAbsoluteDate=this.dateTime;
  //       }else{
  //       let d = new Date(this.dateModel.dateTime);
  //       // this.showAbsoluteDate = moment(d).locale(this._locale).format(this.localDateFormatMap.get(this.dateTimeFormat.dateFormat) + " " + this.localDateFormatMap.get(this.dateTimeFormat.timeFormat));
  //       this.showAbsoluteDate = this.dateTime;
  //       console.log("absolute date inside apply fun ", this.showAbsoluteDate);
  //       }
  //     // this.StartDateCalenderTrigger.forEach((item) => {
  //     // item.closeMenu();
  //     // });
  //   }
  // }

    // onChangeTime(chosenTime: string){
    //   console.log(chosenTime);
    //   if (chosenTime && chosenTime.trim().length > 0) {
    //     let timeSplitted: Array<string> = chosenTime.split(':');
    //     this.dateModel.dateTime.setHours(parseInt(timeSplitted[0]));
    //     this.dateModel.dateTime.setMinutes(parseInt(timeSplitted[1]));
    //     if (timeSplitted.length === 2) {
    //      this.dateModel.dateTime.setSeconds(0);
    //     }
    //     if (timeSplitted.length === 3) {
    //      this.dateModel.dateTime.setSeconds(parseInt(timeSplitted[2]));
    //     }

    //     // ngOnInit() {

    //     //   if(this.translocoService.getActiveLang() != 'en')
          
    //     //   this._locale = this.translocoService.getActiveLang();
          
    //     //   else
          
    //     //   this._locale = 'en-ie';
          
    //     //   this._adapter.setLocale(this._locale);
          
    //     //   }
    //     this.dateModel.dateTime = new Date(
    //     this.dateModel.dateTime
    //     );
    //   }
    // }

    // onChangeDate(event: any) {
    //   console.log("event = ", event);
    //   if(event instanceof Date){
    //     this.dateModel.dateTime = event;
    //     this.dateTime = event;
    //   }
    //   else if (event instanceof Object && ! (event instanceof Date)){
    //     this.dateTime = event?._d;
    //   }
    //   this.onChangeTime(this.startTime);
    //   this.showAbsoluteDate = 
    //   // this.datePipe.transform(
    //   this.dateTime
    //   console.log("absolute date = ", this.showAbsoluteDate)
    //   // ,this.dateTimeFormat.dateFormatMap.get(this.dateTimeFormat.dateFormat)
    //   // );
    // }
  // getDateFormatString(): string {
  //   if (this._locale === 'en') {
  //     return 'YYYY/MM/DD';
  //   } else if (this._locale === 'es') {
  //     return 'DD/MM/YYYY';
  //   }
  //   return '';
  // }
}
