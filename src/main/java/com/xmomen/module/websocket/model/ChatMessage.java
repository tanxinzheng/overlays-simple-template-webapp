package com.xmomen.module.websocket.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * Created by tanxinzheng on 2018/7/19.
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class ChatMessage {

    private String content;
    private String sender;
    private String receiver;

}
