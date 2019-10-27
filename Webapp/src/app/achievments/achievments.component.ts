import { Component, OnInit } from '@angular/core';
import { Service } from '../services/service.models';
import { Achievment } from './achievments.models';

const servicesMock: Service[] = [
  {
    name: 'Jira'
  },
  {
    name: 'Git'
  },
  {
    name: 'Bamboo'
  },
  {
    name: 'Fancy Service'
  }
];

const achievmentsMock: Achievment[] = [
  {
    name: 'Morning Person',
    complete: 60
  },
  {
    name: 'Busy Person',
    complete: 20
  },
  {
    name: 'Quantity is everything',
    complete: 45
  },
  {
    name: 'Hold my Coffe',
    complete: 85
  }
];

@Component({
  selector: 'app-achievments',
  templateUrl: './achievments.component.html',
  styleUrls: ['./achievments.component.scss']
})
export class AchievmentsComponent implements OnInit {
  public services;
  public achievments;
  constructor() {
    this.services = servicesMock;
    this.achievments = achievmentsMock;
  }

  ngOnInit() {}

  getOverallCompletion(achievments: Achievment[]) {
    let result = 0;
    for (const a of achievments) {
      result += a.complete;
    }
    return result / achievments.length;
  }
}
