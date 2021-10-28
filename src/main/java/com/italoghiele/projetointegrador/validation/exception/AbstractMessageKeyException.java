package com.italoghiele.projetointegrador.validation.exception;

import com.italoghiele.projetointegrador.validation.MessageKey;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractMessageKeyException extends RuntimeException {

    private static final long serialVersionUID = -863629340536108027L;

    /**
     * Chave com os parametros que serão utilizados no mesmo
     */
    private Map<MessageKey, Object[]> errorKeys = new HashMap<MessageKey, Object[]>();

    protected AbstractMessageKeyException(Throwable cause, MessageKey errorKey, Object... messageParameters) {
        super(cause);
        errorKeys.put(errorKey, ArrayUtils.clone(messageParameters));
    }

    protected AbstractMessageKeyException(Map<MessageKey, Object[]> errorKeys) {
        this.errorKeys = errorKeys;
    }

    protected AbstractMessageKeyException(MessageKey errorKey, Object... messageParameters) {
        errorKeys.put(errorKey, ArrayUtils.clone(messageParameters));
    }

    protected AbstractMessageKeyException(AbstractMessageKeyException ex) {
        super(ex);
        this.errorKeys = ex.getErrorKeys();
    }

    protected AbstractMessageKeyException(Throwable e) {
        super(e);
    }

    protected AbstractMessageKeyException(Collection<MessageKey> errorKeys) {

        if (CollectionUtils.isNotEmpty(errorKeys)) {
            for (MessageKey errorKey : errorKeys) {
                this.errorKeys.put(errorKey, null);
            }
        }
    }

    /**
     * Retorna as chaves das mensagens
     *
     * @return
     */
    public Map<MessageKey, Object[]> getErrorKeys() {
        return errorKeys;
    }

    protected void setErrorKeys(Map<MessageKey, Object[]> errorKeys) {
        this.errorKeys = errorKeys;
    }

    @Override
    public String toString() {
        String s = getClass().getName();

        if (MapUtils.isNotEmpty(errorKeys)) {
            return s + ": [errorKeys=" + errorKeys.keySet() + "]";
        }

        return s + ": [errorKeys=" + errorKeys + "]";
    }
}
