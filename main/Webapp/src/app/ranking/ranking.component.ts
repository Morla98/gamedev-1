import { Component, OnInit } from '@angular/core';
import { Rank } from './ranking.models';
import { MatTableDataSource } from '@angular/material';

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
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {
  public dataSource: MatTableDataSource<Rank>;
  public displayedColumns;
  constructor() {
    this.displayedColumns = ['name', 'points', 'monthly', 'daily'];
    this.dataSource = new MatTableDataSource<Rank>(ranks);
  }

  ngOnInit() {}
}
