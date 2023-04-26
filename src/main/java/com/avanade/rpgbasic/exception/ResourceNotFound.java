package com.avanade.rpgbasic.exception;

import java.io.Serial;

public class ResourceNotFound extends InvalidInput {

    @Serial
    private static final long serialVersionUID = -5868980605782668416L;

    public ResourceNotFound(String message) {
        super(message);
    }
}