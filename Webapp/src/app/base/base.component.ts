import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationService } from '../authentication/authentication.service';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.scss']
})
export class BaseComponent implements OnInit {
  public selectedLanguage: string;
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
  }

  ngOnInit() {}

  logout() {
    this.router.navigate(['login']);
  }

  setLanguage(lang: string) {
    this.authenticationService.changeLanguage(lang);
}
}
