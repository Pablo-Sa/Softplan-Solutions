import { MessageModel } from './../../../models/message-model';
import { MessageChatInterface } from "./../../../models/message-chat-model";
import { ChatService } from "./../../../services/chat.service";
import { Component, DoCheck, OnInit } from "@angular/core";

@Component({
  selector: "app-chat-modal",
  templateUrl: "./chat-modal.component.html",
  styleUrls: ["./chat-modal.component.css"],
})
export class ChatModalComponent implements OnInit, DoCheck {
  messageList: Array<MessageChatInterface> = [];
  message:MessageModel;

  constructor(private chatService: ChatService) {
    this.message = new MessageModel();
    this.message.date = Date.now();
    this.chatService.getMesg().subscribe((data) => {
      console.log('Mensagem Recebida')
      console.log(data)
      this.messageList.push(data);
    });
  }

  sendMessage(message: MessageModel) {
    console.log(message)
    this.chatService.sendMsg(message);
  }

  ngOnInit() {}

  ngDoCheck(){
    this.message.date = Date.now();
  }
}
