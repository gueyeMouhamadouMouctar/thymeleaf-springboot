package sn.xarandev.thymeleaf_springboot.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DataNotFoundException extends RuntimeException {

    String message;
}