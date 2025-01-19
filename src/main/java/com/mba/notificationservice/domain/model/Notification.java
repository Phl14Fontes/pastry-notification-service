package com.mba.notificationservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Notification {
    public String message;
}
