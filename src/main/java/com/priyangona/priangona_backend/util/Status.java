package com.priyangona.priangona_backend.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status{
    Active(1),
    DeActive(0);

    public final int value;
}
