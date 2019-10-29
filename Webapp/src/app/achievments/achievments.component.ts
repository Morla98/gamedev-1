import { Component, OnInit } from '@angular/core';
import { Service } from '../services/service.models';
import { Achievment } from './achievments.models';
import { cloneDeep } from 'lodash-es';

const achievmentsMock: Achievment[] = [
  {
    name: 'Morning Person',
    complete: Math.random() * 100,
    description: 'Check in before 8'
  },
  {
    name: 'Busy Person',
    complete: Math.random() * 100,
    description: 'Commit 5 things before Lunch'
  },
  {
    name: 'Quantity is everything',
    complete: Math.random() * 100,
    description: 'Reach 500 Commits'
  },
  {
    name: 'Hold my Coffe',
    complete: Math.random() * 100,
    description: 'Check in before 7'
  }
];

const servicesMock: Service[] = [
  {
    name: 'Jira',
    achievments: cloneDeep(achievmentsMock)
  },
  {
    name: 'Git',
    achievments: cloneDeep(achievmentsMock)
  },
  {
    name: 'Bamboo',
    achievments: cloneDeep(achievmentsMock)
  },
  {
    name: 'Fancy Service',
    achievments: cloneDeep(achievmentsMock)
  }
];

@Component({
  selector: 'app-achievments',
  templateUrl: './achievments.component.html',
  styleUrls: ['./achievments.component.scss']
})
export class AchievmentsComponent implements OnInit {
  public services: Service[];
  public achievments;
  public selectedService: string;
  constructor() {
    this.services = servicesMock;
    this.services[0].achievments.push({
      name: 'Jira Expert',
      complete: 23,
      description: 'Edit 400 Tickets'
    });
    this.services[1].achievments.push({
      name: 'Git Expert',
      complete: 30,
      description: 'Push 150 Changes'
    });
    this.services[2].achievments.push({
      name: 'Bamboo Expert',
      complete: 12,
      description: 'Have 100 successfull builds'
    });
    this.services[3].achievments.push({
      name: 'Fancy Expert',
      complete: 100,
      description: 'Be super fancy'
    });
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

  selectService(name: string) {
    this.selectedService = name;
  }

  getSelectedAchievments(){
    const service =  this.services.find(s => s.name === this.selectedService);
    if (service !== undefined){
      return service.achievments;
    }
    return [];
  }
}
