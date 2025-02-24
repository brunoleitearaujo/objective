package com.araujo.objective.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ObjectiveException extends RuntimeException {

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Objective internal server error");

        return pb;
    }
}
