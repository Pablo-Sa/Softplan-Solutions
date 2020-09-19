import { EditModalPeopleComponent } from "./../modals/edit-modal-people/edit-modal-people.component";
import { ExclusionModalPeopleComponent } from "./../modals/exclusion-modal-people/exclusion-modal-people.component";
import { PeopleService } from "./../../services/people.service";
import { PeopleInterface } from "./../../models/people-interface";
import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { MatSnackBar } from "@angular/material/snack-bar";
import { MatDialog } from "@angular/material/dialog";
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: "app-search-people",
  templateUrl: "./search-people.component.html",
  styleUrls: ["./search-people.component.css"],
})
export class SearchPeopleComponent implements OnInit {
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  ELEMENT_DATA: PeopleInterface[] = [];
  displayedColumns: string[] = [
    "name",
    "sexo",
    "email",
    "dateOfBirth",
    "naturalness",
    "nationality",
    "cpf",
    "editar",
    "excluir",
  ];

  dataSource = new MatTableDataSource();
  completeLoading: boolean;
  errorLoading: boolean;

  constructor(
    private peopleService: PeopleService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private router: Router
  ) {
    this.loadPeople();
  }

  ngOnInit() {}

  openSnackBar(message: string) {
    this.snackBar.open(message, "Ok", {
      duration: 2000,
      verticalPosition: "top",
    });
  }

  updateRow(object: PeopleInterface) {
    const dialogRef = this.dialog.open(EditModalPeopleComponent, {
      data: object,
      maxHeight: "600px",
    });

    dialogRef.afterClosed().subscribe((result: PeopleInterface) => {
      if (result) {
        console.log(result);

        this.peopleService.put(result).subscribe(
          (sucess) => {
            this.completeLoading = false;
            this.loadPeople();
            this.openSnackBar("Registro Atualizado Com Sucesso.");
          },
          (error: HttpErrorResponse) => {
            console.log(error);
            if (error.status == 403) {
              this.openSnackBar(`Sessão Expirada, Efetue Login Novamente.`);
              this.router.navigate(["/"]);
            }

            if (error.status == 400) {
              this.openSnackBar(
                `Error: ${error.error[0].field}: ${error.error[0].error}`
              );
            }
          }
        );
      }
    });
  }

  deleteRow(id: string) {
    const dialogRef = this.dialog.open(ExclusionModalPeopleComponent);

    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      if (result) {
        this.peopleService.delete(id).subscribe(
          (sucess) => {
            this.completeLoading = false;
            this.loadPeople();
            this.openSnackBar("Registro Excluído com Sucesso.");
          },
          (error) => {
            this.openSnackBar(`Erro ao Excluír o Registro: ${error.message}`);
            if (error.status == 403) {
              this.openSnackBar(`Sessão Expirada, Efetue Login Novamente.`);
              this.router.navigate(["/"]);
            }
          }
        );
      }
    });
  }

  loadPeople() {
    this.peopleService.getAll().subscribe(
      (data) => {
        console.log(data);
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.completeLoading = true;
        this.ELEMENT_DATA = data;
        this.paginator._intl.itemsPerPageLabel = "Items por Página";
      },
      (error: HttpErrorResponse) => {
        this.errorLoading = true;
        console.log(this.errorLoading);
        console.log(error);
        if (error.status == 403) {
          this.openSnackBar(`Sessão Expirada, Efetue Login Novamente.`);
          this.router.navigate(["/"]);
        }

        if (error.status == 400) {
          this.openSnackBar(
            `Error: ${error.error[0].field}: ${error.error[0].error}`
          );
        }
      }
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
