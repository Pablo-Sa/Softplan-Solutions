import { MessageModel } from './../models/message-model';
import { MessageChatInterface } from './../models/message-chat-model';
import { Observable } from 'rxjs';
import { Injectable } from "@angular/core";
import * as io from 'socket.io-client';

@Injectable({
  providedIn: "root",
})
export class ChatService {
  
  private socket = io('http://localhost:3000');

  sendMsg(msg:MessageModel) {
    this.socket.emit('new-message', msg);
  }

  getMesg(){
    let observable = new Observable<MessageModel>(observer =>{
      this.socket.on('new-message', (data:MessageModel)=>{
        observer.next(data);
      });
        return () => {this.socket.disconnect();}
    });

    return observable;
  }
}
