import { Component, OnInit } from '@angular/core';
import {CommonService} from '../common.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-bank',
  templateUrl: './add-bank.component.html',
  styleUrls: ['./add-bank.component.css']
})
export class AddBankComponent implements OnInit {

  constructor(private bank:CommonService) { }
  alert:boolean=false;
  add= new FormGroup({
    category: new FormControl(''),
    ruleName: new FormControl(''),
    conditions: new FormControl(''),
    fees: new FormControl(''),
    code: new FormControl(''),
  });

  ngOnInit(): void {
  }

  onSubmit() {
    this.bank.addRule(this.add.value).subscribe((result)=>{
      console.log(result);
      this.alert=true;
      this.add.reset();
    })
  }

  closeAlert(){
    this.alert=false;
  }

}
