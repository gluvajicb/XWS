import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {TokenStorageService} from '../services/token-storage/token-storage.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private tokenStorageService: TokenStorageService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const isLoggedIn = !!this.tokenStorageService.getToken();

    if (isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      if (user) {
        if (route.data.roles && !route.data.roles.some(r => user.roles.includes(r))) {
          this.router.navigate(['/']);
          return false;
        }

        return true;
      }
    }

    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
