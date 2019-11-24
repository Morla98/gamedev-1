import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public user;
  constructor(public translateService: TranslateService) {
    this.user = {name: 'Jhon Doe', email: 'JhonDoes@email.com'};
   }

  ngOnInit() {
  }

}
