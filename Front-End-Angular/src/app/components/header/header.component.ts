import { GitUrlModalComponent } from './../modals/git-url-modal/git-url-modal.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

  openModal(){
    const dialogRef = this.dialog.open(GitUrlModalComponent);
  }

}
