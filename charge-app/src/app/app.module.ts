import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddBankComponent } from './add-bank/add-bank.component';
import { UpdateBankComponent } from './update-bank/update-bank.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ListBankComponent } from './list-bank/list-bank.component';

import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule, FormsModule  } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';


@NgModule({
  declarations: [
    AppComponent,
    AddBankComponent,
    UpdateBankComponent,
    LoginComponent,
    RegisterComponent,
    ListBankComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
