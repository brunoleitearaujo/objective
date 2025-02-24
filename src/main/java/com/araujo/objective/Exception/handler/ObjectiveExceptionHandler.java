package com.araujo.objective.Exception.handler;

import com.araujo.objective.Exception.ObjectiveException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectiveExceptionHandler {

    @ExceptionHandler(ObjectiveException.class)
    public ProblemDetail handleObjectiveException(ObjectiveException e) {
        return e.toProblemDetail();
    }
}
