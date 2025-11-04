package com.automationexercise.apis;

import com.automationexercise.Validations.Validation;
import com.automationexercise.Validations.Verification;
import com.automationexercise.utils.Logs.LogsManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {
    RequestSpecification requestSpecification;
    Response response;
    Verification verification;

    public UserManagementAPI(){
        //constructor
        requestSpecification = RestAssured.given();
        verification = new Verification();
    }

    //endPoints
    private static  final String createAccount_endpoint = "/createAccount";
    private static final String deleteAccount_endpoint = "/deleteAccount";
    private static final String updateAccount_endpoint = "/updateAccount";

    //Api  Methods

    @Step("Create a new user account with minimal details")
    public  UserManagementAPI createRegisterUserAccount(String name,String email,
                                                        String password
                                                        ,String firstName,
                                                        String lastName
                                                       ){


        Map <String, String> formParams = new HashMap<>();
        formParams.put("name", name);
        formParams.put("email", email);
        formParams.put("password", password);
        formParams.put("title","Mr");
        formParams.put("birth_date","1");
        formParams.put("birth_month","January");
        formParams.put("birth_year","1990");
        formParams.put("firstname", firstName);
        formParams.put("lastname", lastName);
        formParams.put("company","company");
        formParams.put("address1","address1");
        formParams.put("address2", "address2");
        formParams.put("country", "India");
        formParams.put("zipcode", "123456");
        formParams.put("state", "state");
        formParams.put("city", "city");
        formParams.put("mobile_number", "123456789");
        response = requestSpecification.spec(Builder.getUserManagementRequestspecification(formParams))
                .post(createAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        return this;
    }

@Step("Create a new user account with full details")
public  UserManagementAPI createRegisterUserAccount(String name,String email,
                                                    String password, String title,
                                                    String birth_date, String birth_month,
                                                    String birth_year,String firstName,
                                                    String lastName, String company,
                                                    String address1, String address2,
                                                    String country, String zipcode,
                                                    String state, String city, String phone_number){

    Map <String, String> formParams = new HashMap<>();
    formParams.put("name", name);
    formParams.put("email", email);
    formParams.put("password", password);
    formParams.put("title", title);
    formParams.put("birth_date", birth_date);
    formParams.put("birth_month", birth_month);
    formParams.put("birth_year", birth_year);
    formParams.put("firstname", firstName);
    formParams.put("lastname", lastName);
    formParams.put("company", company);
    formParams.put("address1", address1);
    formParams.put("address2", address2);
    formParams.put("country", country);
    formParams.put("zipcode", zipcode);
    formParams.put("state", state);
    formParams.put("city", city);
    formParams.put("mobile_number", phone_number);
    response = requestSpecification.spec(Builder.getUserManagementRequestspecification(formParams))
            .post(createAccount_endpoint);
    LogsManager.info(response.asPrettyString());
    return this;
}

@Step("Delete User account by email")
public  UserManagementAPI deleteUserAccount(String email,String password){
    Map<String,String> formParam =new HashMap<>();
    formParam.put("email", email);
    formParam.put("password",password);
   response= requestSpecification.spec(Builder.getUserManagementRequestspecification(formParam))
            .delete(deleteAccount_endpoint);
    LogsManager.info(response.asPrettyString());
    return  this;

}


    //Validation
    @Step("Verify that user created successfully")
    public  UserManagementAPI verifyUserCreatedSuccessfully(){

    verification.Equals(response.jsonPath().get("message"),
               "User created!",
            "User is not created successfully");

    return this;
    }

    @Step("Verify that user deleted successfully")
    public  UserManagementAPI verifyUserDeletedSuccessfully(){
    verification.Equals(response.jsonPath().get("message"),"Account deleted!","User is not deleted successfully");
    return this;
    }
}
