package com.kiosk.server.domain.order.data;

import lombok.Getter;

@Getter
public enum OrderType {
    FOR_HERE(0), TAKE_OUT(1);

    private final int value;

    OrderType(int value) {
        this.value = value;
    }
}
