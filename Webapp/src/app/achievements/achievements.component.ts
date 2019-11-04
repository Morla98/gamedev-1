import { Component, OnInit } from '@angular/core';
import { Service } from '../services/service.models';
import { Achievement } from './achievements.models';
import { cloneDeep } from 'lodash-es';

const achievementsMock: Achievement[] = [
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
    name: 'Hold my Coffee',
    complete: Math.random() * 100,
    description: 'Check in before 7'
  }
];

const servicesMock: Service[] = [
  {
    name: 'Jira',
    achievements: cloneDeep(achievementsMock)
  },
  {
    name: 'Git',
    achievements: cloneDeep(achievementsMock)
  },
  {
    name: 'Bamboo',
    achievements: cloneDeep(achievementsMock)
  },
  {
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
  public selectedService: string;
  constructor() {
    this.services = servicesMock;
    if (this.services[0].achievements.length <= 5) {
      this.services[0].achievements.push({
        name: 'Jira Expert',
        complete: 23,
        description: 'Edit 400 Tickets'
      });
      this.services[1].achievements.push({
        name: 'Git Expert',
        complete: 30,
        description: 'Push 150 Changes'
      });
      this.services[2].achievements.push({
        name: 'Bamboo Expert',
        complete: 12,
        description: 'Have 100 successfull builds'
      });
      this.services[3].achievements.push({
        name: 'Fancy Expert',
        complete: 100,
        description: 'Be super fancy'
      });
    }
    this.achievements = achievementsMock;
  }

  ngOnInit() {}

  getOverallCompletion(achievements: Achievement[]) {
    let result = 0;
    for (const a of achievements) {
      result += a.complete;
    }
    return result / achievements.length;
  }

  selectService(name: string) {
    this.selectedService = name;
  }

  getSelectedAchievements() {
    const service = this.services.find(s => s.name === this.selectedService);
    if (service !== undefined) {
      return service.achievements;
    }
    return [];
  }
}
