import { Component } from "@angular/core";
import { Router } from '@angular/router';
import { LoginService } from './services/login.service';
import { TokenService } from './services/token.service';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  title = "softPlanFront";

  showHeader: boolean = false;

  constructor(
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.loginService.showMenuEmitter.subscribe(
      (response) => (this.showHeader = response)
    );
  }
}
