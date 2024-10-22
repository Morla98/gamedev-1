import { ProfileComponent } from '../profile/profile.component';
import { Routes } from '@angular/router';
import { BaseComponent } from './base.component';
import { RankingComponent } from '../ranking/ranking.component';
import { AchievementsComponent } from '../achievements/achievements.component';

export const baseRoutes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
      { path: 'profile', pathMatch: 'full', component: ProfileComponent },
      { path: 'ranking', pathMatch: 'full', component: RankingComponent },
      { path: 'achievements', pathMatch: 'full', component: AchievementsComponent },
      { path: '**', redirectTo: 'achievements' }
    ]
  }
];
