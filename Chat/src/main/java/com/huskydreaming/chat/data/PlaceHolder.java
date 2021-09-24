package com.huskydreaming.chat.data;

public enum PlaceHolder {
    PLAYER,
    DISPLAYNAME,
    WORLD,
    DATE,
    ONLINE,
    MAX,
    MESSAGE;

    public String format() {
        return "{" + name().toLowerCase() + "}";
    }
}
