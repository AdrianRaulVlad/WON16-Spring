package org.fasttrack.countries.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{
    private final long entityId;

    public EntityNotFoundException(String message, long entityId) {
        super(message);
        this.entityId = entityId;
    }
}