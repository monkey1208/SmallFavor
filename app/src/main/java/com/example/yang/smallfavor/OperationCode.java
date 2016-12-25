package com.example.yang.smallfavor;

/**
 * Created by Yang on 2016/12/23.
 */
public class OperationCode {
    String opcode;
    public OperationCode(String opcode){
        switch(opcode){
            case "REG":
                opcode = "REG";
                break;
            case "LOGIN":
                opcode = "LOGIN";
                break;
            case "REQ":
                opcode = "REQUEST";
                break;
            case "RES":
                opcode = "RES";
                break;
            default:
                opcode = "NONE";
        }
    }
}
