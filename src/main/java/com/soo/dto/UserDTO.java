package com.soo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Size;

/**
 * @author KHS
 */
public class UserDTO {

    @Data
    public static class Request {
        @NotBlank
        @Size(min = 6)
        private String name;
        @NotBlank
        private String password;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    public static class Response extends ResourceSupport {
        //Hateoas id error
        private int number;
        private String name;
    }

    @Data
    public static class Update {
        private String name;
        private String password;
    }
}
