import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddBankComponent } from './add-bank/add-bank.component';
import { UpdateBankComponent } from './update-bank/update-bank.component';
import { ListBankComponent } from './list-bank/list-bank.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminComponent} from './admin/admin.component'

const routes: Routes = [
  {component: AddBankComponent,path:'add'},
  { component: UpdateBankComponent, path: 'update' },
  { component: ListBankComponent, path: 'list' },
  { component: LoginComponent, path: 'login' },
  { component: RegisterComponent, path: 'register' },
  {component: AdminComponent, path: 'admin' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
