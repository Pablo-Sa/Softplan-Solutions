import { WebSocketAPI } from './../../../shared/WebSocketAPI';
import { MessageModel } from './../../../models/message-model';
import { MessageChatInterface } from "./../../../models/message-chat-model";
import { Component, DoCheck, OnInit } from "@angular/core";

@Component({
  selector: "app-chat-modal",
  templateUrl: "./chat-modal.component.html",
  styleUrls: ["./chat-modal.component.css"],
  preserveWhitespaces: true
})
export class ChatModalComponent implements OnInit, DoCheck {
  messageList: Array<MessageChatInterface> = [];
  message:MessageModel;
  disabledButtonConnect:boolean = false;

  constructor(private webSocketAPI: WebSocketAPI) {
    this.message = new MessageModel();
    this.message.date = Date.now();
  }

  ngOnInit() {
    this.webSocketAPI.newMessageChat.subscribe((data) =>{
      this.messageList.push(data.message);
    })
  }

  ngDoCheck(){
    this.message.date = Date.now();
  }

  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send(this.message);
  }

  disabledButtonConnectionChat(){
    this.disabledButtonConnect = !this.disabledButtonConnect;
  }

}
