import { LogPeopleInterface } from './../../models/log-people-interface';
import { PeopleService } from './../../services/people.service';
import { PeopleInterface } from './../../models/people-interface';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-log-people',
  templateUrl: './log-people.component.html',
  styleUrls: ['./log-people.component.css']
})
export class LogPeopleComponent implements OnInit {

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  ELEMENT_DATA: LogPeopleInterface[] = [];
  displayedColumns: string[] = [
    "idPerson",
    "namePerson",
    "dateOfCreate",
    "dateOfUpdate"
  ];

  dataSource = new MatTableDataSource();
  completeLoading: boolean;
  errorLoading: boolean;

  constructor(
    private peopleService: PeopleService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.loadPeopleLogs();
  }

  ngOnInit() {}

  openSnackBar(message: string) {
    this.snackBar.open(message, "Ok", {
      duration: 2000,
      verticalPosition: "top",
    });
  }

  loadPeopleLogs() {
    this.peopleService.getLogs().subscribe(
      (data:any) => {
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
