import { SearchPeopleComponent } from './components/search-people/search-people.component';
import { AuthGuard } from './guards/auth.guard';
import { PeopleComponent } from './components/people/people.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate:[AuthGuard] },
  { path: 'people', component: PeopleComponent,canActivate:[AuthGuard] },
  { path: 'searchpeople', component: SearchPeopleComponent,canActivate:[AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
