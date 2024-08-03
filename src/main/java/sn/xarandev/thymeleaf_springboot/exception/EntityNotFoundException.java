package sn.xarandev.thymeleaf_springboot.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EntityNotFoundException extends RuntimeException {

    String message;
}