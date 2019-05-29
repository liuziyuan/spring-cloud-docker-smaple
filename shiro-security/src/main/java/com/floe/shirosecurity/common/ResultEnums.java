package com.floe.shirosecurity.common;

import lombok.Getter;

@Getter
public enum ResultEnums {
	SUCCESS("0x0000", "SUCCESS");
	
	private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
