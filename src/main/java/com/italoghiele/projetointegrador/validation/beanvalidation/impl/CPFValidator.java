package com.italoghiele.projetointegrador.validation.beanvalidation.impl;

import com.italoghiele.projetointegrador.validation.beanvalidation.CPFValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValid, String>  {

    private String value;

    @Override
    public void initialize(CPFValid constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String strCpf, ConstraintValidatorContext constraintValidatorContext) {

        int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
        int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
        String strDigitoVerificador, strDigitoResultado;

        try{
            for (int iCont = 1; iCont < strCpf.length() -1; iCont++) {
                iDigitoCPF = Integer.valueOf(strCpf.substring(iCont -1, iCont)).intValue();
                iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
            }
            iRestoDivisao = (iDigito1Aux % 11);
            if (iRestoDivisao < 2) {
                iDigito1 = 0;
            } else {
                iDigito1 = 11 - iRestoDivisao;
            }
            iDigito2Aux += 2 * iDigito1;
            iRestoDivisao = (iDigito2Aux % 11);
            if (iRestoDivisao < 2) {
                iDigito2 = 0;
            } else {
                iDigito2 = 11 - iRestoDivisao;
            }
            strDigitoVerificador = strCpf.substring(strCpf.length()-2, strCpf.length());
            strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
            strDigitoVerificador.equals(strDigitoResultado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
