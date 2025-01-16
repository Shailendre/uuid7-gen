package info.lazycompiler.uuid7_gen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class ServerExceptionHandler {

    @ExceptionHandler(UUIDGenerationException.class)
    public ResponseEntity<ServerException> handle(UUIDGenerationException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServerException(e.getMessage()));
    }

    public record ServerException(String message) {}

}
