import {
  Component,
  OnInit,
  ChangeDetectorRef,
  ChangeDetectionStrategy
} from '@angular/core';
import { Rank } from './ranking.models';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { UserControllerService } from 'src/api/services';
import { User } from 'src/api/models';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

const ranks: Rank[] = [
  {
    name: 'Donald Quack',
    points: 54,
    monthly: 12,
    daily: 2
  },
  {
    name: 'Daisy Schlumpf',
    points: 43,
    monthly: 8,
    daily: 1
  },
  {
    name: 'Max MusterPerson',
    points: 87,
    monthly: 28,
    daily: 6
  },
  {
    name: 'Jhon Doe',
    points: 60,
    monthly: 40,
    daily: 10
  }
];

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RankingComponent implements OnInit {
  public dataSource: MatTableDataSource<User>;
  public displayedColumns;
  constructor(
    private api: UserControllerService,
    cd: ChangeDetectorRef,
    private matSnackBar: MatSnackBar
  ) {
    this.displayedColumns = ['userName', 'level', 'score'];
    this.api
      .getAllUsersUsingGET()
      .pipe(
        map(data => {
          if (data !== undefined) {
            this.dataSource = new MatTableDataSource<User>(data);
            cd.markForCheck();
          }
        }),
        catchError(err => {
          this.matSnackBar.open(err.message);
          return of(undefined);
        })
      )
      .subscribe(data => {});
  }

  ngOnInit() {}
}
