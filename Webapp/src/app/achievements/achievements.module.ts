import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AchievementsComponent } from './achievements.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [AchievementsComponent],
  imports: [
    SharedModule
  ]
})
export class AchievementsModule { }