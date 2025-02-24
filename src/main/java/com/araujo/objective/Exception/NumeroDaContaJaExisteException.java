package com.araujo.objective.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NumeroDaContaJaExisteException extends ObjectiveException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("O numero da conta já existe");

        return pb;
    }
}
