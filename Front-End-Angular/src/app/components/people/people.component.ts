import { PeopleServiceService } from './../../services/people-service.service';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PeopleInterface } from 'src/app/models/people-interface';
import { DatePickerPeopleComponent } from '../date-picker-people/date-picker-people.component';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }],
  changeDetection: ChangeDetectionStrategy.OnPush,
  preserveWhitespaces: true
})
export class PeopleComponent implements OnInit {
  datePicker = DatePickerPeopleComponent;

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  sexo: string[] = ['Masculino','Feminino'];

  constructor(private _formBuilder: FormBuilder,
              private _snackBar: MatSnackBar,
              private peopleServiceService: PeopleServiceService,
              private router: Router) { }

  openSnackBar(message: string,) {
    this._snackBar.open(message, 'Ok',{
      duration: 2000,
      verticalPosition:'top'
    });
  }              

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      name: ['', Validators.required],
      sexo: ['', Validators.required]
    });

    this.secondFormGroup = this._formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      naturalness: ['', Validators.required],
      nationality: ['', Validators.required],
      cpf: ['', Validators.required]
    });
  }

  savePerson(){
    const newPerson:PeopleInterface =  {
          ...this.firstFormGroup.value,
          "email":this.secondFormGroup.controls['email'].value,
          "dateOfBirth":this.secondFormGroup.controls['dateOfBirth'].value,
          "naturalness":this.secondFormGroup.controls['naturalness'].value,
          "nationality": this.secondFormGroup.controls['nationality'].value,
          "cpf": this.secondFormGroup.controls['cpf'].value
        };

    console.log(newPerson);

    this.peopleServiceService.post(newPerson)
    .subscribe(
      sucess => this.openSnackBar(`Novo Registro Salvo Com Sucesso`),
      (error:HttpErrorResponse) => {
        console.log(error);
        if(error.status == 403){
          this.openSnackBar(`Sessão Expirada, Efetue Login Novamente.`);
          this.router.navigate(["/"]);
        }

        if(error.status == 400){
          this.openSnackBar(`Error: ${error.error[0].error}`);
        }
      }
      );
      
    this.firstFormGroup.reset();
    this.secondFormGroup.reset();
  }


}
