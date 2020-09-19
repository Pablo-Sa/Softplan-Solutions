import { UserInterface } from "../models/user-interface";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subject, Observable } from "rxjs";
import { TokenService } from "./token.service";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  private serverAPI: string = `${environment.API}/auth`;
  public showMenuEmitter = new Subject<boolean>();

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  authenticate(user: UserInterface): Observable<any> {
    return this.http.post(this.serverAPI, user);
  }

  unAuthenticatedUser(valor: boolean) {
    this.showMenuEmitter.next(!valor);
  }

  userIsAuthenticated(): boolean {
    const ExistsToken = this.tokenService.hasToken();

    if (ExistsToken) {
      this.showMenuEmitter.next(true);
      return true;
    } else {
      return false;
    }
  }
}
