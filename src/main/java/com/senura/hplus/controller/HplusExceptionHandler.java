package com.senura.hplus.controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;

public class HplusExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType type = null;
        if(ex instanceof DataIntegrityViolationException){
            type = ErrorType.BAD_REQUEST;
        }else{
            type = ErrorType.INTERNAL_ERROR;
        }
        return GraphqlErrorBuilder.newError(env).message("Received message" + ex.getMessage()).errorType(type).build();
    }
}
