import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { ServicesComponent } from './services/services.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import { AppGuard } from './app.guard';

const routes: Routes = [
  { path: 'login', pathMatch: 'full', component: AuthenticationComponent },
  {
    path: '',
    loadChildren: './base/base.module#BaseModule',
    canActivate: [AppGuard],
    canLoad: [AppGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
