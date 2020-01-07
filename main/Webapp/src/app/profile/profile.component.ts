import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { TranslateService } from '@ngx-translate/core';
import { Subscription, of } from 'rxjs';
import { catchError, filter } from 'rxjs/operators';
import { UserControllerService } from 'src/api/services';
import { AuthenticationService } from '../authentication/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public user;

  private subs: Subscription[] = [];
  constructor(
    public translateService: TranslateService,
    private authService: AuthenticationService,
    private api: UserControllerService,
    private matSnackBar: MatSnackBar
  ) {
    this.subs.push(
      this.authService.user$
        .pipe(filter(data => data !== undefined))
        .subscribe(data => (this.user = data))
    );
    this.authService.loadUserInfo();
  }

  ngOnInit() {}

  save() {
    this.api
      .addUserUsingPUT(this.user)
      .pipe(
        catchError(err => {
          this.matSnackBar.open(err.message);
          return of(undefined);
        })
      )
      .subscribe(data => {});
  }
}
