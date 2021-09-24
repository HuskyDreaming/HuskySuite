package com.huskydreaming.chat.data;

public enum Config {
    // TITLE
    TITLE_JOIN_ENABLED,
    TITLE_JOIN_HEADER,
    TITLE_JOIN_FOOTER,
    TITLE_WELCOME_ENABLED,
    TITLE_WELCOME_HEADER,
    TITLE_WELCOME_FOOTER,

    // MESSAGES
    MESSAGES_JOIN_ENABLED,
    MESSAGES_JOIN_MESSAGE,
    MESSAGES_QUIT_ENABLED,
    MESSAGES_QUIT_MESSAGE,
    MESSAGES_WELCOME_ENABLED,
    MESSAGES_WELCOME_MESSAGES,

    //CHAT
    CHAT_FORMAT,
    CHAT_HOVER,
    CHAT_COMMAND;

    public String format() {
        return this.name().toLowerCase().replace("_", ".");
    }
}
