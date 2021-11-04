package it.test.spring.testfabrick.model;

import lombok.*;

import java.util.List;

@Data
public class Transiction {

    private String status;
    private List<String> error;
    private Payload payload;

    private static class Payload {
        private List<WireTransfer> list;
    }
}
