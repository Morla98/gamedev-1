import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationControllerService } from 'src/api/services';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class AuthenticationService {
  constructor(
    private translateService: TranslateService,
    private api: AuthenticationControllerService,
    private router: Router
  ) {}

  changeLanguage(lang: string) {
    this.translateService.use(lang);
  }

  login(email: string, password: string) {
    this.api.loginUsingGET({ email, password }).subscribe(res => {
      if (res !== undefined) {
        sessionStorage.setItem('jwt', res);
        this.router.navigate(['achievements']);
      } else {
        throw new Error('Got no token from login request!');
      }
    });
  }
}
