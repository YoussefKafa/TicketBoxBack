package com.project.tb.exceptions;
import com.project.tb.exceptions.TeamUniqueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@ControllerAdvice //mechanism helps us to let all controllers come
// here to take advice if there is any thing went wrong
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler
    public final ResponseEntity<Object> handleTeamUniqueException(TeamUniqueException ex, WebRequest req){
        TeamUniqueExceptionResponse teamUniqueExceptionResponse=new TeamUniqueExceptionResponse(ex.getMessage());
        return new ResponseEntity(teamUniqueExceptionResponse,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleUserUniqueException(UserUniqueException ex, WebRequest req){
        UserUniqueExceptionResponse userUniqueExceptionResponse=new UserUniqueExceptionResponse(ex.getMessage());
        return new ResponseEntity(userUniqueExceptionResponse,HttpStatus.BAD_REQUEST);

    }
}