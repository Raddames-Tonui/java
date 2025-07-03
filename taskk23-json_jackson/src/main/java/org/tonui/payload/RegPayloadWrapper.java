package org.tonui.payload;

public class RegPayloadWrapper {
    private String action;
    private Payload payload;

    public String getAction(){return action;}
    public void setAction(String action){this.action=action;}

    public Payload getPayload(){return payload;}
//    public void setPayload(Payload payload){this.payload=payload;}
}
