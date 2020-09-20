import { ChatModalComponent } from './../modals/chat-modal/chat-modal.component';
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

  openDialogChat() {
    const dialogRef = this.dialog.open(ChatModalComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  openModal(){
    const dialogRef = this.dialog.open(GitUrlModalComponent);
  }

}
