package br.com.softplan.poc.websocket.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HelloMessage {

    private String user;
    private String body;
    private Long date;
    
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("user", user)
				.append("body", body)
				.append("date", date)
				.toString();
	}
    
}
