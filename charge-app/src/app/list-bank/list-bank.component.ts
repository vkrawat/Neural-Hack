import { Component, OnInit } from '@angular/core';
import {CommonService} from '../common.service'

@Component({
  selector: 'app-list-bank',
  templateUrl: './list-bank.component.html',
  styleUrls: ['./list-bank.component.css']
})
export class ListBankComponent implements OnInit {
  public collection:any;

  constructor(private commonService:CommonService) { }

  ngOnInit(): void {
    this.commonService.getBankList().subscribe((result)=>{
      this.collection=result;
      console.log(this.collection);
    });
  }

}
