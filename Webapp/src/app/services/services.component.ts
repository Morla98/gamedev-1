import { Component, OnInit } from '@angular/core';
import { Service } from './service.models';

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

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss']
})
export class ServicesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }


  
}
