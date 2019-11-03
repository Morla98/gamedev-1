import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Route
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable()
export class AppGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    return this.checkToken();
  }

  canLoad(route: Route): boolean | Observable<boolean> {
    return this.checkToken();
  }

  checkToken() {
    // TODO Check/Verify Token
    return true;
  }
}
