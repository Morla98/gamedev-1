import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable()
export class AuthenticationService {
  constructor(private translateService: TranslateService) {}

  changeLanguage(lang: string) {
    this.translateService.use(lang);
  }
}
