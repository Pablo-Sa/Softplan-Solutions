import { ExplanatoryVideoOfProjectModalComponent } from './../modals/explanatory-video-of-project-modal/explanatory-video-of-project-modal.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
    this.dialog.open(ExplanatoryVideoOfProjectModalComponent);
  }


}
