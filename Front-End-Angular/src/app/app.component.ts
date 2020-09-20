import { MessageChatInterface } from './models/message-chat-model';
import { ChatService } from "./services/chat.service";
import { Component } from "@angular/core";
import { LoginService } from "./services/login.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  title = "softPlanFront";

  userOfMessage: string;
  messageList:Array<MessageChatInterface> = [];
  showHeader: boolean = false;

  constructor(private loginService: LoginService, private chatService: ChatService) {
    this.chatService.getMesg().subscribe(data =>{
      this.messageList.push(data)
    })
  }

  ngOnInit() {
    this.loginService.showMenuEmitter.subscribe(
      (response) => (this.showHeader = response)
    );
  }

  sendMessage(message:MessageChatInterface) {
    this.chatService.sendMsg(message);
  }
}
