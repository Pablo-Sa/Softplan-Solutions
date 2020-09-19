import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-git-url-modal',
  templateUrl: './git-url-modal.component.html',
  styleUrls: ['./git-url-modal.component.css']
})
export class GitUrlModalComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  redirectGit(){
    window.location.href = 'https://github.com/Pablo-Sa/Softplan-Solutions';
  }

  redirectMyPortfolio(){
    window.location.href = 'http://www.ondeployment.com.br/';
  }
}
