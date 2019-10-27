import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AchievmentsComponent } from './achievments.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [AchievmentsComponent],
  imports: [
    SharedModule
  ]
})
export class AchievmentsModule { }
