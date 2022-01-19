package com.vuan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseMessage {
	private String message;

	public ResponseMessage() {
	}

	public ResponseMessage(String message) {
		this.message = message;
	}
}
