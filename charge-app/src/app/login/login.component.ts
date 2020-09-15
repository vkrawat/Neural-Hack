import { CommonService } from '../common.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public collection: any;
  public flag: boolean = false;
  alert: boolean = false;

  constructor(private commonService: CommonService) { }

  login = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),

  });

  ngOnInit(): void {
    this.commonService.getUserList().subscribe((result) => {
      this.collection = result;
      // console.log(this.collection);
    });
  }

  onSubmit() {
      this.check(this.login.value);
      this.login.reset();
  }

  check(result) {
    this.flag = false;
    for (let i = 0; i < this.collection.length; i++) {
      if (result["email"] === this.collection[i]["email"] && result["password"] === this.collection[i]["password"]) {
        console.log(result["email"] + " " + this.collection[i]["email"]);
        console.log("Yes");
        this.flag = true;
        this.alert=true;
        break;
      }
    }

    if (this.flag === false)
      {console.log("No");
      alert("Wrong Email or Password");}
  }

  closeAlert() {
    this.alert = false;
  }

}
