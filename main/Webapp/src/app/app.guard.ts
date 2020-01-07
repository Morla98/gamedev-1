import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Route,
  Router
} from '@angular/router';
import { Observable } from 'rxjs';
import { isNull } from 'util';

@Injectable()
export class AppGuard implements CanActivate {
  constructor(private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.checkToken()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  canLoad(route: Route): boolean | Observable<boolean> {
    if (this.checkToken()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  checkToken() {
    const token = sessionStorage.getItem('jwt');
    if (!isNull(token) && token !== undefined) {
      return true;
    }
    return false;
  }
}
