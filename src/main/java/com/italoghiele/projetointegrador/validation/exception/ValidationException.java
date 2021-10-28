package com.italoghiele.projetointegrador.validation.exception;

import com.italoghiele.projetointegrador.validation.MessageKey;

import java.util.Map;

public class ValidationException extends AbstractMessageKeyException{

    private static final long serialVersionUID = 2989499390486881385L;

    public ValidationException(Map<MessageKey,Object[]> errorKeys){
        super(errorKeys);
    }

    public ValidationException(Throwable cause, MessageKey bundleKey,
                               Object... messageParameters) {
        super(cause, bundleKey, messageParameters);
    }

    public ValidationException(MessageKey bundleKey, Object... messageParameters) {
        super(bundleKey, messageParameters);
    }

    public ValidationException(AbstractMessageKeyException exception) {
        super(exception);
    }

    @Override
    public String getMessage() {
        return getErrorKeys().toString();
    }
}
