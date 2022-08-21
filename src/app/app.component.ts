import { Component, LOCALE_ID } from '@angular/core';
import * as moment from 'moment';
import CurrencyList from 'currency-list';
// import * as data from '../../assets/Curr.json';
import  data from 'src/assets/Curr.json';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  b:String;
  c:String;
  currentRowData: any;
// localDateFormatMap: Map<string, string> = new Map<string, string>([
//   ['DD Month YYYY', 'DD MMMM YYYY'],
//   ['DD/MM/YYYY', 'DD/MM/YYYY'],
//   ['YYYY/MM/DD', 'YYYY/MM/DD'],
//   ['DD-MM-YYYY', 'DD-MM-YYYY'],
//   ['YYYY-MM-DD', 'YYYY-MM-DD'],
//   ['DD.MM.YYYY', 'DD.MM.YYYY'],
//   ['MM/DD/YYYY', 'MM/DD/YYYY'],
//   ['MM-DD-YYYY', 'MM-DD-YYYY'],
//   ['DD Mon YY', 'DD MMM YY'],
//   ['YYYY, Month DD', 'YYYY, MMMM DD'],
//   ['HH:mm', 'HH:mm'],
//   ['hh:mm AM/PM', 'hh:mm a'],
//   ['HH:mm:ss', 'HH:mm:ss'],
//   ['hh:mm:ss AM/PM', 'hh:mm:ss a'],
// ]);

  // title = 'new-project';
  //  d = new Date();
  // d1 = "2022, junio 04 12:02:03 PM";
  // // today=moment(this.d).locale('es').format('MMMM DD YY'); DD Month YYYY
  //  d2 = moment("2022, junio 23 12:02:15 PM").locale('en-ie').format('YYYY, MMMM DD' + ' ' + 'hh:mm:ss a');
  // today=moment("2022, junio 04 12:02:03 PM").locale('en-ie').format('YYYY, MMMM DD' + ' ' + 'hh:mm:ss a' );
   ids: [];
  singlerow: any;
  listofList:any = [{}];
  // list = { 
  //   name1: "",
  //   symbol_native: "",
  //   symbol: "",
  //   code: "",
  //   name_plural: "",
  //   rounding: "",
  //   decimal_digits: ""
  // };

  fun(){
   var  a = CurrencyList.getAll();
    // console.log(a);
  this.currentRowData = JSON.parse(JSON.stringify(a));
console.log(this.currentRowData); 
  // Object.values(this.currentRowData).forEach(val => {
  //   console.log(JSON.parse(JSON.stringify(val)) )
  //   // console.log(JSON.parse(JSON.stringify(val)).name );
  //   // console.log(JSON.parse(JSON.stringify(val)).symbol );
  
  // });
  

// for(let result of this.currentRowData.af){
//   this.singlerow = JSON.parse(JSON.stringify(result));
//   //  this.ids.push(result);
//   // console.log(result);
//   console.log(this.singlerow)
// }

Object.values(this.currentRowData.en_IN).forEach(val => {
  let list = { 
    name: "",
    // symbol_native: "",
    symbol: "",
    code: "",
    name_plural: "",
    rounding: "",
    decimal_digits: ""
  };
    // console.log("name = " + JSON.parse(JSON.stringify(val)).name );
    // this.list.name1
    list.name =  JSON.parse(JSON.stringify(val)).symbol + " " + JSON.parse(JSON.stringify(val)).name;
    // console.log("symbol = " + JSON.parse(JSON.stringify(val)).symbol );
    list.code = JSON.parse(JSON.stringify(val)).code;
    list.decimal_digits = JSON.parse(JSON.stringify(val)).decimal_digits;
    list.name_plural = JSON.parse(JSON.stringify(val)).name_plural;
    list.rounding = JSON.parse(JSON.stringify(val)).rounding;
    list.symbol = JSON.parse(JSON.stringify(val)).symbol;
    // list.symbol_native = JSON.parse(JSON.stringify(val)).symbol_native;
    // console.log(this.list);
    // this.listofList.
    // let 
    this.listofList.push(list);
    // console.log("symbol_native = " + JSON.parse(JSON.stringify(val)).symbol_native );
    console.log(" ///");
    // console.log(this.listofList);
  });
   console.log(
   CurrencyList.getAll()
  
  // this.currentRowData.af

  //  CurrencyList.getAll('en_US') // Prints all currency lists for en_US locale
  // CurrencyList.get("USD")
   );
  //  console.log(this.currentRowData.cs.USD)
  console.log(this.listofList);
 


}
read(){
  // const mode = require('./FileJson/powers.json');
for(let i = 0;i<data.length;i++){
    console.log(data[i].name);
}
}

}

