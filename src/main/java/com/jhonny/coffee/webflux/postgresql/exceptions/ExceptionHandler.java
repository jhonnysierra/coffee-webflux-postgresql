package com.jhonny.coffee.webflux.postgresql.exceptions;

import com.jhonny.coffee.webflux.postgresql.exceptions.model.DomainException;
import com.jhonny.coffee.webflux.postgresql.exceptions.model.ErrorResponseDetail;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
@Component
@Order(-2)
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {
    /**
     * Create a new {@code AbstractErrorWebExceptionHandler}.
     *
     * @param errorAttributes    the error attributes
     * @param resources          the resources configuration properties
     * @param applicationContext the application context
     * @since 2.4.0
     */
    public ExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext,
                            ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

        Throwable throwable = getError(request);
        DomainException domainException = (DomainException) throwable;

        return buildErrorResponse(domainException, request);
    }

    private static Mono<ServerResponse> buildErrorResponse(DomainException domainException, final ServerRequest request) {
        ErrorResponseDetail errorResponseDetail = ErrorResponseDetail.builder()
                .code(String.valueOf(domainException.getErrorMessage().getStatusCode()))
                .message(domainException.getMessage())
                .domain(request.path())
                .build();
        return ServerResponse.status(domainException.getErrorMessage().getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errorResponseDetail);
    }
}
