import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { cloneDeep } from 'lodash-es';
import {
  AchievementControllerService,
  CollectorControllerService,
  UserAchievementControllerService
} from 'src/api/services';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import {
  Achievement,
  Collector,
  PreviewDto,
  AchievementDto
} from 'src/api/models';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.scss']
})
export class AchievementsComponent implements OnInit {
  public services: Collector[];
  public previews: PreviewDto[];
  public userEmail: string;
  public achievements;

  private _selectedServiceId: string;

  constructor(
    private achievementService: UserAchievementControllerService,
    private cd: ChangeDetectorRef,
    private collectorService: CollectorControllerService,
    private matSnackBar: MatSnackBar
  ) {
    // Looking in the Browser Storage for a login credential
    const foundMail = sessionStorage.getItem('email');
    if (foundMail !== undefined) {
      this.userEmail = foundMail;
    }

    this.loadServices();
    this.loadPreviews();
  }

  // getter and setter helps to also load achievement every time the selected Service/Collector changes
  set selectedService(id: string) {
    this._selectedServiceId = id;
    this.loadAchievements();
  }
  get selectedService(): string {
    return this._selectedServiceId;
  }

  ngOnInit() {}

  // Making a Http request to our main Backend to get all Achievements for a certain Service
  loadAchievements() {
    const currentService = this.services.find(
      s => s.id === this.selectedService
    );
    if (currentService !== undefined) {
      this.achievementService
        .getUserAchievementsByCollectorIdUsingGET(currentService.id)
        .pipe(
          map(data => {
            if (data !== undefined) {
              this.achievements = data.sort((a, b) =>
                a.name.localeCompare(b.name)
              );

              this.cd.markForCheck();
            }
          }),
          catchError(err => {
            this.matSnackBar.open(err.message);
            console.error(err);
            return of(undefined);
          })
        )
        // subcribing here because the Observable is lazy and the call will otherwise not be made at all
        .subscribe(data => {});
    }
  }

  // Making a Http request to our main Backend to get all previews for services/collectors
  loadPreviews() {
    this.achievementService
      .getUserAchievementsPreviewUsingGET()
      .pipe(
        map(data => {
          if (data !== undefined) {
            this.previews = data;
            this.cd.markForCheck();
          }
        }),
        catchError(err => {
          this.matSnackBar.open(err.message);
          return of(undefined);
        })
      )
      // subscribing, since the observable is lazy
      .subscribe(data => {});
  }


  //Making a Http request to our main Backend to get all services/collectors
  loadServices() {
    this.collectorService
      .getAllCollectorsUsingGET()
      .pipe(
        map(data => {
          if (data !== undefined) {
            this.services = data;
            this.cd.markForCheck();
          }
        }),
        catchError(err => {
          this.matSnackBar.open(err.message);
          return of(undefined);
        })
      )
      // subscribing, since the observable is lazy
      .subscribe(data => {});
  }

  getOverallCompletion(achievements: AchievementDto[]) {
    let result = 0;
    for (const a of achievements) {
      result += a.progress;
    }
    return result / achievements.length;
  }

  selectService(id: string) {
    this.selectedService = id;
    this.loadAchievements();
  }

  backToOverview() {
    this.selectedService = undefined;
  }
}
