import { LoginService } from "../services/login.service";
import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from "@angular/router";
import { Observable } from "rxjs";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate {
  constructor(
    private loginService: LoginService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  openSnackBar(message: string) {
    this.snackBar.open(message, "Ok", {
      duration: 2000,
      verticalPosition: "top",
    });
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    if (this.loginService.userIsAuthenticated()) {
      return true;
    }

    this.openSnackBar('Sessão Expirada, Efetue Login Novamente.')
    this.router.navigate([""]);

    return false;
  }
}
