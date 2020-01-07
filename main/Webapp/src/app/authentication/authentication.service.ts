import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {
  AuthenticationControllerService,
  UserControllerService
} from 'src/api/services';
import { tap, catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { HttpResponse } from '@angular/common/http';
import { of, Subject } from 'rxjs';
import { User } from 'src/api/models';

@Injectable()
export class AuthenticationService {
  public user$: Subject<User> = new Subject<User>();

  constructor(
    private translateService: TranslateService,
    private api: AuthenticationControllerService,
    private userapi: UserControllerService,
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
          if (res !== undefined) {
            sessionStorage.setItem('jwt', res);
            sessionStorage.setItem('email', email);
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

  logout() {
    this.router.navigate(['login']);
    sessionStorage.clear();
  }

  loadUserInfo() {
    this.userapi
      .getProfileUsingGET()
      .pipe(
        map(data => {
          if (data !== undefined) {
            this.user$.next(data);
          }
        }),
        catchError(err => {
          this.snackBar.open(err.message);
          return of(undefined);
        })
      )
      .subscribe(data => {});
  }
}
