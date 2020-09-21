import { PeopleService } from './../../services/people.service';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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

  constructor(private formBuilder: FormBuilder,
              private snackBar: MatSnackBar,
              private peopleService: PeopleService,
              private router: Router) { }

  openSnackBar(message: string,) {
    this.snackBar.open(message, 'Ok',{
      duration: 4000,
      verticalPosition:'top'
    });
  }              

  ngOnInit() {
    this.firstFormGroup = this.formBuilder.group({
      name: [''],
      sexo: ['']
    });

    this.secondFormGroup = this.formBuilder.group({
      email: [''],
      dateOfBirth: [''],
      naturalness: [''],
      nationality: [''],
      cpf: ['']
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

    this.peopleService.post(newPerson)
    .subscribe(
      sucess => this.openSnackBar(`Novo Registro Salvo Com Sucesso`),
      (error:HttpErrorResponse) => {
        console.log(error);
        if(error.status == 403){
          this.openSnackBar(`Sessão Expirada, Efetue Login Novamente.`);
          this.router.navigate(["/"]);
        }

        if(error.status == 400){
          if(error.error[0].field){
            this.openSnackBar(`Error: ${error.error[0].field}: ${error.error[0].error}`);
          }else{
            this.openSnackBar(`Error: ${error.error}`)
          }
        }
      }
      );
      
    this.firstFormGroup.reset();
    this.secondFormGroup.reset();
  }


}
