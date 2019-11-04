import { Component, OnInit } from '@angular/core';
import { Service } from './service.models';

const servicesMock: Service[] = [
  {
    name: 'Jira',
    achievements: []
  },
  {
    name: 'Git',
    achievements: []
  },
  {
    name: 'Bamboo',
    achievements: []
  },
  {
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
