import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BolnicaComponent } from './components/bolnica/bolnica.component';
import { OdeljenjeComponent } from './components/odeljenje/odeljenje.component';
import { PacijentComponent } from './components/pacijent/pacijent.component';

const routes: Routes =  [ { path: 'bolnica', component: BolnicaComponent },   
{ path: 'odeljenje', component: OdeljenjeComponent },
{ path: 'pacijent', component: PacijentComponent },
{ path: '', redirectTo: '/bolnica', pathMatch: 'full'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }