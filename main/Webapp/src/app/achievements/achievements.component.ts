import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Service } from '../services/service.models';
import { cloneDeep } from 'lodash-es';
import { AchievementControllerService } from 'src/api/services';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { Achievement } from 'src/api/models';

const achievementsMock: Achievement[] = [
  {
    name: 'Morning Person',
    description: 'Check in before 8'
  },
  {
    name: 'Busy Person',
    description: 'Commit 5 things before Lunch'
  },
  {
    name: 'Quantity is everything',
    description: 'Reach 500 Commits'
  },
  {
    name: 'Hold my Coffee',
    description: 'Check in before 7'
  }
];

const servicesMock: Service[] = [
  {
    id: 0,
    name: 'Jira',
    achievements: cloneDeep(achievementsMock)
  },
  {
    id: 1,
    name: 'Git',
    achievements: cloneDeep(achievementsMock)
  },
  {
    id: 2,
    name: 'Bamboo',
    achievements: cloneDeep(achievementsMock)
  },
  {
    id: 3,
    name: 'Fancy Service',
    achievements: cloneDeep(achievementsMock)
  }
];

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.scss']
})
export class AchievementsComponent implements OnInit {
  public services: Service[];
  public achievements;
  private _selectedService: string;
  constructor(
    private achievementService: AchievementControllerService,
    private cd: ChangeDetectorRef
  ) {
    this.services = servicesMock;
    this.achievements = achievementsMock;
  }

  set selectedService(name: string) {
    this._selectedService = name;
    this.loadAchievements();
  }
  get selectedService(): string {
    return this._selectedService;
  }
  ngOnInit() {}

  loadAchievements() {
    const currentService = this.services.find(
      s => s.name === this.selectedService
    );
    if (currentService !== undefined) {
      this.achievementService
        .getAchievementByCollectorIdUsingGET(currentService.id)
        .pipe(
          map(data => {
            console.log(data);
            if (data !== undefined) {
              this.achievements = data;
              this.cd.markForCheck();
            }
          }),
          catchError(err => {
            //this.matSnackBar.open(err.message);
            console.error(err);
            return of(undefined);
          })
        )
        .subscribe(data => {});
    }
  }

  getOverallCompletion(achievements: Achievement[]) {
    let result = 0;
    for (const a of achievements) {
      //result += a.completion;
    }
    return result / achievements.length;
  }

  selectService(name: string) {
    this.selectedService = name;
    this.loadAchievements();
  }

  getSelectedAchievements() {
    const service = this.services.find(s => s.name === this.selectedService);
    if (service !== undefined) {
      return service.achievements;
    }
    return [];
  }
}
