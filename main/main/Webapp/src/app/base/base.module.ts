import { NgModule } from '@angular/core';
import { ProfileComponent } from '../profile/profile.component';
import { SharedModule } from '../shared/shared.module';
import { BaseComponent } from './base.component';
import { RouterModule } from '@angular/router';
import { baseRoutes } from './base.routes';
import { RankingModule } from '../ranking/ranking.module';
import { AchievementsModule } from '../achievements/achievements.module';

@NgModule({
  declarations: [BaseComponent, ProfileComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(baseRoutes),
    RankingModule,
    AchievementsModule
  ]
})
export class BaseModule { }
