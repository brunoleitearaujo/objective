package com.araujo.objective.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class SaldoInsuficienteException extends ObjectiveException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Saldo insufuciente");

        return pb;
    }
}
