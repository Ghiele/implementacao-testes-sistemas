package com.italoghiele.projetointegrador.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return fieldMessages;
    }

    public void addError(String fieldMessage, String message){
        fieldMessages.add(new FieldMessage(fieldMessage, message));
    }
}
