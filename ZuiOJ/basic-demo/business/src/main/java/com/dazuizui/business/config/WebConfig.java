package com.dazuizui.business.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;


public class WebConfig {

public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//设置日期格式
ObjectMapper objectMapper = new ObjectMapper();
mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
//设置中文编码格式
List<MediaType> list = new ArrayList<MediaType>();
list.add(MediaType.APPLICATION_JSON_UTF8);
mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
return mappingJackson2HttpMessageConverter;
}
}