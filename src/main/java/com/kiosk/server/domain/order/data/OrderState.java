package com.kiosk.server.domain.order.data;

import lombok.Getter;

@Getter
public enum OrderState {
    READY(0), FINISH(1), END(2);

    private final int value;

    OrderState(int value) {
        this.value = value;
    }
}
