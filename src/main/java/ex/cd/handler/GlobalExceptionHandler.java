package ex.cd.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Validation 검증 관련 오류 핸들러
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errorDefaultMessages = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();
        //validation 을 수행한 객체명 정보 추출
        String objectName = e.getObjectName();
        //validation 체크를 실패한 정보를 추출한다. 필드명과 defaultmessage
        e.getFieldErrors()
                .forEach(error -> errorDefaultMessages.put(error.getField(), error.getDefaultMessage()));

        log.error("failure check request validation, uri: {}, objectName: {}, {}", request.getRequestURI(), objectName, errors);
        return ResponseEntity.badRequest().body(errorDefaultMessages);
    }
}
