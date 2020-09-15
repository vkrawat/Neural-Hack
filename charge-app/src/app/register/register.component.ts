import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private commonService: CommonService) { }

  alert: boolean = false;

  register = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });

  ngOnInit(): void {
  }

  onSubmit() {
    this.commonService.addUser(this.register.value).subscribe((result) => {
      console.log(result);
      this.alert = true;
      this.register.reset();
    })
  }

  closeAlert() {
    this.alert = false;
  }

}
