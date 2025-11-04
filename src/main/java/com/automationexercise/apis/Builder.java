package com.automationexercise.apis;

import com.automationexercise.utils.DataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Builder {

    private static String baseURI= PropertyReader.getProperty("baseUrlApi");


    private Builder() {}

    //Build request Specification
    public  static RequestSpecification getUserManagementRequestspecification(Map<String,?>formParams){
        return  new RequestSpecBuilder().setBaseUri(baseURI)
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }
}
