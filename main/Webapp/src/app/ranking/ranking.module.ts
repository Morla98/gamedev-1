import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { RankingComponent } from './ranking.component';

@NgModule({
  declarations: [RankingComponent],
  imports: [
    SharedModule
  ]
})
export class RankingModule { }
