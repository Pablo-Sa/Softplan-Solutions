import { STEPPER_GLOBAL_OPTIONS } from "@angular/cdk/stepper";
import { Component, Inject, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { PeopleInterface } from "src/app/models/people-interface";
import { DatePickerPeopleComponent } from "../../date-picker-people/date-picker-people.component";

@Component({
  selector: "app-edit-modal-people",
  templateUrl: "./edit-modal-people.component.html",
  styleUrls: ["./edit-modal-people.component.css"],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: { showError: true },
    },
  ],
  preserveWhitespaces: true,
})
export class EditModalPeopleComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  currentPerson: PeopleInterface;
  sexo: string[] = ["Masculino", "Feminino"];
  datePicker = DatePickerPeopleComponent;

  constructor(
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA) private data: PeopleInterface
  ) {
    console.log(this.data);
  }

  ngOnInit() {
    this.firstFormGroup = this.formBuilder.group({
      id: [this.data.id],
      name: [this.data.name],
      sexo: [this.data.sexo],
    });

    this.secondFormGroup = this.formBuilder.group({
      email: [this.data.email],
      dateOfBirth: [this.data.dateOfBirth],
      naturalness: [this.data.naturalness],
      nationality: [this.data.nationality],
      cpf: [this.data.cpf],
    });
  }

  ngDoCheck() {
    this.currentPerson = {
      ...this.firstFormGroup.value,
      ...this.secondFormGroup.value,
    };
  }
}
