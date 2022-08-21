// import { SelectionModel } from '@angular/cdk/collections';
import { Component, EventEmitter, Inject, Input, OnInit, Output, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatMenuTrigger } from '@angular/material/menu';
import { Observable, Subscription } from 'rxjs';
import { DatePipe } from '@angular/common';
import {
  MAT_MOMENT_DATE_FORMATS,
  MomentDateAdapter,
  MAT_MOMENT_DATE_ADAPTER_OPTIONS,
} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import 'moment/locale/en-ie';
import 'moment/locale/es';

@Component({
  selector: 'app-toolbarcomponent',
  templateUrl: './toolbarcomponent.component.html',
  styleUrls: ['./toolbarcomponent.component.css'],
  providers: [
    DatePipe,
    {provide: MAT_DATE_LOCALE, useValue: 'en'},

    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS],
    },
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})
export class ToolbarcomponentComponent implements OnInit {

  @ViewChildren('StartDateCalenderTrigger') StartDateCalenderTrigger: QueryList<MatMenuTrigger>;
  @ViewChildren('EndDateCalenderTrigger') EndDateCalenderTrigger: QueryList<MatMenuTrigger>;
  // @ViewChild('searchBar', { static: false }) persistentSearchComponent: PersistentSearchComponent;
  @ViewChild('startDateCalendar', { static: false }) startDateCalendar: any;
  @ViewChild('endDateCalendar', { static: false }) endDateCalendar: any;

  userName: string;
  tenantId: string;
  isReset: boolean;
  preAppliedJsonData: any = {};
  isPanelOpen: boolean;
  // selection = new SelectionModel(true, []);
  selectedFilters: any[] = [];
  subscription: Subscription;
  displayedColumns: string[] = [];
  copyDisplayedCol = [];
  basicFilter: FormControl = new FormControl();
  filteredOptions: Observable<string[]>;
  searchText: string = '';

  enablefilters: boolean;
  fieldArray = [];
  HeaderDataTypes = [];
  stringDataype = ['String', 'Text', 'LongString'];
  boolDataType = ['Boolean'];
  searchKeyword: string = '';
  searchOptionsWithoutArrow;
  filterapplied: boolean;
  preserveSelectedField = [];
  removeFields: number[] = [];
  dateFieldArray =  [{object :{ dataype: "Timestamp", end: "04/05/2022 15:03:07", filetrType: "Absolute" },dataype: "Timestamp",end: "04/05/2022 15:03:07",endTime: "15:03:07",entry: "",filetrType: "Absolute",from: "",fromtime: 1,interval: "",label: "time_stamp",selectedEndDate: new Date(),selectedStartDate: new Date() ,start: "11/05/2022 03:45:07",
  startTime: "03:45:07",to: "",totime: 1
  }];

  tempFieldArray: any[];
  dateDataTypes = ['Time', 'Date', 'Timespan', 'DateTime', 'Timestamp'];
  minDateAccountExpiry = new Date();

  localDateFormatMap: Map<string, string> = new Map<string, string>([
    ['DD Month YYYY', 'DD MMMM YYYY'],
    ['DD/MM/YYYY', 'DD/MM/YYYY'],
    ['YYYY/MM/DD', 'YYYY/MM/DD'],
    ['DD-MM-YYYY', 'DD-MM-YYYY'],
    ['YYYY-MM-DD', 'YYYY-MM-DD'],
    ['DD.MM.YYYY', 'DD.MM.YYYY'],
    ['MM/DD/YYYY', 'MM/DD/YYYY'],
    ['MM-DD-YYYY', 'MM-DD-YYYY'],
    ['DD Mon YY', 'DD MMM YY'],
    ['YYYY, Month DD', 'YYYY, MMMM DD'],
    ['HH:mm', 'HH:mm'],
    ['hh:mm AM/PM', 'hh:mm a'],
    ['HH:mm:ss', 'HH:mm:ss'],
    ['hh:mm:ss AM/PM', 'hh:mm:ss a'],
  ]);


  dateFilterType: any = ['Absolute', 'Relative', 'Quick'];

  QuickFilterTypes: any = ['Time', 'Day', 'Week', 'Month', 'Year'];

  RelativeFilterTypes: any = ['Minutes ago', 'Hours ago', 'Days ago', 'Weeks ago', 'Months ago', 'Years ago'];

  QuickChooseEntryTypes = [
    {
      Time: ['Last 15 Minutes', 'Last 30 Minutes', 'Last 1 Hour', 'Last 4 Hours', 'Last 12 Hours', 'Last 24 Hours'],

      Day: ['Yesterday', 'Today', 'This Day Last Week', 'Last 7 Days', 'Last 30 Days', 'Last 60 Days', 'Last 90 Days', 'The Day So Far', 'Day Before Yesterday'],

      Week: ['Week To Date', 'This Week', 'Previous Week'],

      Month: ['Month To Date', 'This Month', 'Previous Month', 'Last 6 Months'],

      Year: ['Year To Date', 'This Year', 'Previous Year'],
    },
  ];
  QuickfilterEntryDropdown = [];
  stateChangeBool: boolean = false;
  selectedDateFilter: string;
  // searchOptionModelWithoutArrow: NgpPersistentSearchModel = {
  //   showGoback: false,
  //   selectedText: '',
  //   options: [],
  // };
  QuickFilterDropdownMOdel = {};
  RelativeFilterDropdownMOdel = {};
  // QuickfilterEntryDropdownModel = {
  //   type: 'single-select',
  //   labelName: this.translocoService.translate('data-table-with-toolbar.Entry'),
  //   selected: '',
  //   options: [],
  //   showCrossIcon: true,
  //   dropdownWidth: '226px',
  //   translate: true
  // };
  appliedButtonClicked: boolean = false;
  initialFieldArray: Array<any> = [];
  initialDateFieldArray: Array<any> = [];

  @Input() basicFilterContext: any;
  @Output() basicFilterComponentDestroyed = new EventEmitter<object>();

  columnNameToDataProperty: Map<string, string> = new Map();
  dataPropertyToColumnName: Map<string, string> = new Map();
  contextId: any;
  validate: boolean;
  validateFormField: boolean;
  showStartErrorMessage = [];
  showEndErrorMessage = [];
  validateSearch: boolean = false;

  startTimeErrMsg: string[] = [];
  endTimeErrMsg: string[] = [];

  intervalToMultiplierMap: any = {
    'Minutes ago': 1,
    'Hours ago': 60,
    'Days ago': 24 * 60 * 60,
    'Weeks ago': 7 * 24 * 60 * 60,
    'Months ago': 30 * 7 * 24 * 60 * 60,
    'Years ago': 365 * 30 * 7 * 24 * 60 * 60,
  };
  isQuickFilterEntrySelected: boolean = false;

  // userModel: UserModel;
  displayFormatToActualFormat: Map<string, string>;
  timeZone: string = '';

  constructor(private _adapter: DateAdapter<any>,
    @Inject(MAT_DATE_LOCALE) private _locale: string,private datePipe:DatePipe) { }

  // stateChangeBool:boolean;
  // appliedButtonClicked:boolean;
  // dateFieldArray:[]
  
  ngOnInit(): void {
    this._locale = 'es';
    this._adapter.setLocale(this._locale);
    this.dateFieldArray[0].selectedStartDate =new Date();
    this.dateFieldArray[0].selectedEndDate =new Date();
  }

  checkDateEmpty(str) {
    let dateString = str.split(' ');
    return (str.split(' ')[0] == '' || str.split(' ')[1] == '') && this.appliedButtonClicked ? true : false;
  }

  enableReset() {
    this.stateChangeBool = true;
    // this.showStartErrorMessage[index] = this.showEndErrorMessage[index] = false;
  }
DateMenuOpened() {
    let pattern = /(\d{2})\/(\d{2})\/(\d{4})/;
    this.dateFieldArray.forEach((ele) => {
      if (ele.start !== '') this.startDateCalendar.activeDate = new Date(ele.start.replace(pattern, '$3-$2-$1'));
      if (ele.end !== '') this.endDateCalendar.activeDate = new Date(ele.end.replace(pattern, '$3-$2-$1'));
    });
  }
onChangeStartDate(event, index) {
    this.isReset = true;
    this.showStartErrorMessage[index] = false;
    this.startTimeErrMsg[index] = '';
    this.stateChangeBool = true;
    console.log("onChangeStartDate event = " + event + "onChangeStartDate event?._d = " + event?._d);
    if(event instanceof Date){
      this.dateFieldArray[index].selectedStartDate = event;
      this.dateFieldArray[index].start = '';
      this.dateFieldArray[index].start =
      this.datePipe.transform(event, 'dd/MM/yyyy').toString() + ' ' + this.dateFieldArray[index].startTime;
    }
       
 else if (event instanceof Object  && ! (event instanceof Date)){
      this.dateFieldArray[index].selectedStartDate = event?._d;
      this.dateFieldArray[index].start = '';
      this.dateFieldArray[index].start =
      this.datePipe.transform( event?._d, 'dd/MM/yyyy').toString() + ' ' + this.dateFieldArray[index].startTime;
    }
    if (this.dateFieldArray[index].start.includes('undefined')) {
      let splitted = this.dateFieldArray[index].start.split('undefined');
      if (splitted.length > 2) {
        this.dateFieldArray[index].start = splitted[0] + '' + splitted[2];
      } else {
        this.dateFieldArray[index].start = splitted[0];
      }
    }
    this.onChangeStartTime(index, this.dateFieldArray[index].startTime);
  }

 applyStartDate(index) {
    this.isReset = true;
    this.stateChangeBool = true;

    if (!this.dateFieldArray[index].startTime && this.dateFieldArray[index].dataype !== 'Date') {
      this.showStartErrorMessage[index] = true;
      this.startTimeErrMsg[index] = 'Please enter the start time ';
    } else if (
      this.dateFieldArray[index].startTime &&
      this.dateFieldArray[index].startTime.length == 0 &&
      this.dateFieldArray[index].dataype !== 'Date'
    ) {
      this.showStartErrorMessage[index] = true;
      this.startTimeErrMsg[index] = 'Please enter the start time ';
    } else if (this.dateFieldArray[index].start == '') {
      this.showStartErrorMessage[index] = true;
      this.startTimeErrMsg[index] = 'Please select the start date ';
    } else {
      this.showStartErrorMessage[index] = false;
      this.startTimeErrMsg[index] = ' ';
      if (this.dateFieldArray[index].start) {
        let splitted = this.dateFieldArray[index].start.split(' ');
        this.dateFieldArray[index].start = splitted[0];
      }
      this.dateFieldArray[index].start = this.dateFieldArray[index].start + ' ' + this.dateFieldArray[index].startTime;
      if (this.dateFieldArray[index].start.includes('undefined')) {
        let splitted = this.dateFieldArray[index].start.split('undefined');
        if (splitted.length > 2) {
          this.dateFieldArray[index].start = splitted[0] + '' + splitted[2];
        } else {
          this.dateFieldArray[index].start = splitted[0];
        }
      }

      this.StartDateCalenderTrigger.forEach((item) => {
        item.closeMenu();
      });
    }
  }
onChangeEndDate(event, index) {
    this.showEndErrorMessage[index] = false;
    console.log("onChangeEndDate event = " + event + "onChangeEndDate event?._d = " + event?._d);
    if(event instanceof Date){
      this.dateFieldArray[index].selectedEndDate = event;
      this.selectedDate(event.toString());
      this.isReset = true;
      this.stateChangeBool = true;
      this.endTimeErrMsg[index] = '';
      this.dateFieldArray[index].end = '';
      this.dateFieldArray[index].end =
        this.datePipe.transform(event, 'dd/MM/yyyy').toString() + ' ' + this.dateFieldArray[index].endTime;
    }
     else if (event instanceof Object  && ! (event instanceof Date)){
        this.dateFieldArray[index].selectedEndDate = new Date(event?._d);
        this.selectedDate(event?._d.toString());
        this.isReset = true;
        this.stateChangeBool = true;
        this.endTimeErrMsg[index] = '';
        this.dateFieldArray[index].end = '';
        this.dateFieldArray[index].end =
          this.datePipe.transform(event?._d, 'dd/MM/yyyy').toString() + ' ' + this.dateFieldArray[index].endTime;
    }
    // this.selectedDate(event.toString());
    // this.isReset = true;
    // this.stateChangeBool = true;
    // this.endTimeErrMsg[index] = '';
    // this.dateFieldArray[index].end = '';
    // this.dateFieldArray[index].end =
    //   this.datePipe.transform(event, 'dd/MM/yyyy').toString() + ' ' + this.dateFieldArray[index].endTime;
    if (this.dateFieldArray[index].end.includes('undefined')) {
      let splitted = this.dateFieldArray[index].end.split('undefined');
      if (splitted.length > 2) {
        this.dateFieldArray[index].end = splitted[0] + '' + splitted[2];
      } else {
        this.dateFieldArray[index].end = splitted[0];
      }
    }
    this.onChangeEndTime(index, this.dateFieldArray[index].endTime);
  }
onChangeEndTime(dateFieldIndex: number, chosenTime: any): void {
    if (chosenTime && chosenTime.trim().length > 0) {
      let timeSplitted: Array<string> = chosenTime.split(':');

      this.dateFieldArray[dateFieldIndex].selectedEndDate.setHours(parseInt(timeSplitted[0]));
      this.dateFieldArray[dateFieldIndex].selectedEndDate.setMinutes(parseInt(timeSplitted[1]));
      if (timeSplitted.length === 2) {
        this.dateFieldArray[dateFieldIndex].selectedStartDate.setSeconds(0);
      }
      if (timeSplitted.length === 3) {
        this.dateFieldArray[dateFieldIndex].selectedEndDate.setSeconds(parseInt(timeSplitted[2]));
      }
      this.dateFieldArray[dateFieldIndex].selectedEndDate = new Date(
        this.dateFieldArray[dateFieldIndex].selectedEndDate
      );
    }
  }
applyEndDate(index) {
    this.isReset = true;
    this.stateChangeBool = true;

    if (!this.dateFieldArray[index].endTime && this.dateFieldArray[index].dataype !== 'Date') {
      this.showEndErrorMessage[index] = true;
      this.endTimeErrMsg[index] = 'Please enter the end time ';
    } else if (
      this.dateFieldArray[index].startTime &&
      this.dateFieldArray[index].startTime.length == 0 &&
      this.dateFieldArray[index].dataype !== 'Date'
    ) {
      this.showEndErrorMessage[index] = true;
      this.endTimeErrMsg[index] = 'Please enter the end time ';
    } else if (this.dateFieldArray[index].end.length == 0) {
      this.showEndErrorMessage[index] = true;
      this.endTimeErrMsg[index] = 'Please select the end date';
    } else if (!this.isFilterValid()) {
      this.showEndErrorMessage[index] = true;
      this.endTimeErrMsg[index] ='Selected range is invalid';
    } else {
      this.showEndErrorMessage[index] = false;
      this.endTimeErrMsg[index] = '';
      if (this.dateFieldArray[index].end) {
        let splitted = this.dateFieldArray[index].end.split(' ');
        this.dateFieldArray[index].end = splitted[0];
      }
      this.dateFieldArray[index].end = this.dateFieldArray[index].end + ' ' + this.dateFieldArray[index].endTime;
      if (this.dateFieldArray[index].end.includes('undefined')) {
        let splitted = this.dateFieldArray[index].end.split('undefined');
        if (splitted.length > 2) {
          this.dateFieldArray[index].end = splitted[0] + '' + splitted[2];
        } else {
          this.dateFieldArray[index].end = splitted[0];
        }
      }

      this.EndDateCalenderTrigger.forEach((item) => {
        item.closeMenu();
      });
    }
  }
  onChangeStartTime(dateFieldIndex: number, chosenTime: any): void {
    if (chosenTime && chosenTime.trim().length > 0) {
      let timeSplitted: Array<string> = chosenTime.split(':');

      this.dateFieldArray[dateFieldIndex].selectedStartDate.setHours(parseInt(timeSplitted[0]));
      this.dateFieldArray[dateFieldIndex].selectedStartDate.setMinutes(parseInt(timeSplitted[1]));
      if (timeSplitted.length === 2) {
        this.dateFieldArray[dateFieldIndex].selectedStartDate.setSeconds(0);
      }
      if (timeSplitted.length === 3) {
        this.dateFieldArray[dateFieldIndex].selectedStartDate.setSeconds(parseInt(timeSplitted[2]));
      }
      this.dateFieldArray[dateFieldIndex].selectedStartDate = new Date(
        this.dateFieldArray[dateFieldIndex].selectedStartDate
      );
    }
  }
 selectedDate(event?) {
    return new Date(event);
  }
isFilterValid(): boolean {
    let isValid;
    this.dateFieldArray.forEach((ele) => {
      if (ele.filetrType == 'Absolute') {
        let dateRangeStartDate = new Date(ele.selectedStartDate);
        let dateRangeEndDate = new Date(ele.selectedEndDate);

        dateRangeStartDate.setHours(0, 0, 0, 0);
        dateRangeEndDate.setHours(0, 0, 0, 0);
        ele.selectedStartDate = new Date(ele.selectedStartDate);
        ele.selectedEndDate = new Date(ele.selectedEndDate);

        if (dateRangeStartDate > dateRangeEndDate) {
          isValid = false;
        } else if (dateRangeStartDate < dateRangeEndDate) {
          isValid = true;
        } else isValid = ele.startTime <= ele.endTime;
      } else if (ele.filetrType == 'Relative') {
        let rangeStart: number = ele.fromtime * this.intervalToMultiplierMap[ele.from];
        let rangeEnd: number = ele.totime * this.intervalToMultiplierMap[ele.to];
        isValid = rangeStart >= rangeEnd ? true : false;
      } else {
        isValid = true;
      }
    });
    return isValid;
  }
}
