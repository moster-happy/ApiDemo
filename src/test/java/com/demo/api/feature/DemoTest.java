package com.demo.api.feature;

import com.demo.api.core.BaseTest;
import com.demo.api.dto.request.LoginRequest;
import com.demo.api.dto.request.ResgristerRequestObject;
import com.demo.api.dto.response.GetUserResponse;
import com.demo.api.dto.response.UserData;
import com.demo.api.dto.response.regrister.ResgristerResponseObject;
import com.demo.api.pageobject.ResgristerPage;
import com.demo.api.services.ResgristerService;
import com.demo.api.utils.DataUtils;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoTest extends BaseTest {

    ResgristerService resgristerService;

    @BeforeMethod
    public void setup(){
        resgristerService = new ResgristerService();
    }

//    @Test
//    public void demo() {
//        GetUserResponse userResponse = resgristerService.getAllUser(token);
//
//        List<UserData> userDataList = userResponse.getData();
//
//        List<UserData> filter = userDataList.stream()
//                .filter(u -> u.getId() >= 11143)
//                .collect(Collectors.toList());
//
//        System.out.println(filter);
//    }

    @Test
    public void demoBTVN() {
        // Create user
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        ResgristerRequestObject  resgristerRequest = new ResgristerRequestObject();
        resgristerRequest.setName(DataUtils.getData().getFirstName());

        String email = DataUtils.getData().getEmail();
        int password = DataUtils.getData().getPassword();
        resgristerRequest.setEmail(email);
        resgristerRequest.setPassword(password);
        int ID1 = new ResgristerPage().getId(resgristerRequest);

        // Check user by id;
        resgristerService.getUser(token, ID1);

        // Delete user
        resgristerService.deleteUser(token, ID1);
    }
}
