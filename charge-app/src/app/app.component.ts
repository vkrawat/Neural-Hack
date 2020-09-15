import { Component } from '@angular/core';
import { AdminComponent } from './admin/admin.component'
import { CommonService } from './common.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'charge-app';

  constructor(private commonService: CommonService) { }

  public admin=this.commonService.adminLogIn;

  ngOnInit(): void {
    
  }

  function(){
    return this.commonService.adminLogIn;
  }

  logout(){
    console.log("no")
    this.commonService.adminLogIn=false;
    this.function();
  }


}
