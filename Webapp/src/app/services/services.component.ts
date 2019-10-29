import { Component, OnInit } from '@angular/core';
import { Service } from './service.models';

const servicesMock: Service[] = [
  {
    name: 'Jira',
    achievments: []
  },
  {
    name: 'Git',
    achievments: []
  },
  {
    name: 'Bamboo',
    achievments: []
  },
  {
    name: 'Fancy Service',
    achievments: []
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
