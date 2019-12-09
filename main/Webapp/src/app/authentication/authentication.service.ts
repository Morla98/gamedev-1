import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthenticationControllerService } from 'src/api/services';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';

@Injectable()
export class AuthenticationService {
  constructor(
    private translateService: TranslateService,
    private api: AuthenticationControllerService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  changeLanguage(lang: string) {
    this.translateService.use(lang);
  }

  login(email: string, password: string) {
    this.api
      .loginUsingGET({ email, password })
      .pipe(
        tap(res => {
          this.snackBar.open(this.translateService.instant('LOGIN_FAILED'));
          if (res !== undefined) {
            sessionStorage.setItem('jwt', res);
            this.snackBar.open(this.translateService.instant('LOGIN_SUCCESS'));
            this.router.navigate(['achievements']);
          }
        }),
        catchError(e => {
          console.error('LOGIN FAILED', e);
          this.snackBar.open(this.translateService.instant('LOGIN_FAILED'));
          return of({});
        })
      )
      .subscribe(res => {});
  }
}
