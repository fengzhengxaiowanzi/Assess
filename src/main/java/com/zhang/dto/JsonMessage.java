package com.zhang.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhang
 * @Description: com.zhang.dto
 * @Date：Created in 16:53 2021/1/6
 */
@Data
public class JsonMessage {
    private Integer id;

    private String msg;

    private String location;

    private Map<String, Object> data = new HashMap<>();

}
