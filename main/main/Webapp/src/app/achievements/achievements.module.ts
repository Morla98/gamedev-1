import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AchievementsComponent } from './achievements.component';
import { SharedModule } from '../shared/shared.module';
import { AchievementControllerService } from 'src/api/services';
import { MatProgressBarModule } from '@angular/material/progress-bar';

@NgModule({
  declarations: [AchievementsComponent],
  imports: [
    SharedModule,
    MatProgressBarModule
  ],
  providers:[AchievementControllerService]
})
export class AchievementsModule { }