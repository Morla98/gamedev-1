import { NgModule } from '@angular/core';
import { ProfileComponent } from '../profile/profile.component';
import { ServicesComponent } from '../services/services.component';
import { SharedModule } from '../shared/shared.module';
import { BaseComponent } from './base.component';
import { RouterModule } from '@angular/router';
import { baseRoutes } from './base.routes';

@NgModule({
  declarations: [BaseComponent, ProfileComponent, ServicesComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(baseRoutes),
  ]
})
export class BaseModule { }
