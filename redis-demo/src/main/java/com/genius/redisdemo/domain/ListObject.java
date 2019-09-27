package com.genius.redisdemo.domain;

import lombok.Data;

import java.util.List;

@Data
public class ListObject {
    private String key;
    private List<String> value;
}
