import { Component, OnInit } from '@angular/core';
import { Service } from './service.models';

const servicesMock: Service[] = [
  {
    id: 0,
    name: 'Jira',
    achievements: []
  },
  {
    id: 1,
    name: 'Git',
    achievements: []
  },
  {
    id: 2,
    name: 'Bamboo',
    achievements: []
  },
  {
    id: 3,
    name: 'Fancy Service',
    achievements: []
  }
];

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss']
})
export class ServicesComponent implements OnInit {
  constructor() {}

  ngOnInit() {}
}
