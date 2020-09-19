import { Router } from "@angular/router";
import { LoginService } from "./../../services/login.service";
import { UserInterface } from "./../../models/user-interface";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { TokenService } from "src/app/services/token.service";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  public form: FormGroup;
  private authToken: string;

  constructor(
    private loginService: LoginService,
    private tokenService: TokenService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit() {
    this.tokenService.removeToken();
    this.loginService.unAuthenticatedUser(true);
    this.form = this.formBuilder.group({
      login: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, "Ok", {
      duration: 2000,
      verticalPosition: "top",
    });
  }

  onSubmit() {
    console.log(this.form.value);
    this.loginService.authenticate(this.form.value).subscribe(
      (sucess: any) => {
        console.log(sucess);
        this.authToken = sucess.token;
        this.tokenService.setToken(this.authToken);
        this.loginService.showMenuEmitter.next(true);
        this.openSnackBar(
          `Seja Bem Vindo ${this.form.controls["login"].value}`
        );
        this.router.navigate(["/home"]);
      },
      (error) => {
        console.log(error);
        this.loginService.showMenuEmitter.next(false);
        this.openSnackBar("Credênciais Inválidas");
      }
    );
  }
}
