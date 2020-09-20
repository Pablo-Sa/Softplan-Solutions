package br.com.softplan.poc.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Greeting {

    private HelloMessage message;

}
