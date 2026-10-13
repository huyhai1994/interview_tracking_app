package com.homelab.interview_tracking_app.company.share.exception_handling;


import com.homelab.interview_tracking_app.shared.error_code.ErrorCode;
import com.homelab.interview_tracking_app.shared.exception.InternalException;
import com.homelab.interview_tracking_app.shared.response.ApiError;
import com.homelab.interview_tracking_app.shared.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ApiResponse<Void>> handleInternalServerError(
            Exception exception
    ) {
        log.error(
                "Internal server error: exceptionType={}, message={}",
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                exception
        );

        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR
        );
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(
            HttpStatus status,
            ErrorCode errorCode
    ) {
        ApiError error = new ApiError(
                errorCode.name(),
                errorCode.getDefaultMessage()
        );

        return ResponseEntity
                .status(status)
                .body(ApiResponse.failure(error));
    }
}
