import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from '../authentication/authentication.service';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.scss']
})
export class BaseComponent implements OnInit {
  public selectedLanguage: string;
  public user;

  private subs: Subscription[] = [];
  constructor(
    private router: Router,
    private translateService: TranslateService,
    private authenticationService: AuthenticationService
  ) {
    const currentLang = this.translateService.currentLang;
    if (currentLang !== undefined) {
      this.selectedLanguage = this.translateService.currentLang;
    } else {
      this.selectedLanguage = this.translateService.defaultLang;
    }

    this.subs.push(
      this.authenticationService.user$
        .pipe(filter(data => data !== undefined))
        .subscribe(data => (this.user = data))
    );
    this.authenticationService.loadUserInfo();
  }

  ngOnInit() {}

  logout() {
    this.authenticationService.logout();
  }

  setLanguage(lang: string) {
    this.authenticationService.changeLanguage(lang);
  }
}
