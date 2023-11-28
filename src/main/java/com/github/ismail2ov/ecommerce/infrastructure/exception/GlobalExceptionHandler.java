package com.github.ismail2ov.ecommerce.infrastructure.exception;

import com.github.ismail2ov.ecommerce.domain.BasketNotFoundException;
import com.github.ismail2ov.ecommerce.domain.ProductNotFoundException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ProblemDetail handleProductNotFoundException(ProductNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "Something went wrong!");
    problemDetail.setTitle("Out of Stock");
    problemDetail.setType(URI.create("https://example.org/out-of-stock"));
    return problemDetail;
  }

  @ExceptionHandler(BasketNotFoundException.class)
  public ProblemDetail handleBasketNotFoundException(BasketNotFoundException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
        "Something went wrong!");
    problemDetail.setTitle("Basket not found!");
    problemDetail.setType(URI.create("https://example.org/basket-not-found"));
    return problemDetail;
  }

}
