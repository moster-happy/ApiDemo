package com.demo.api.services;

import com.demo.api.apipath.DemoPath;
import com.demo.api.core.BaseApi;
import com.demo.api.dto.request.LoginRequest;
import com.demo.api.dto.request.ResgristerRequestObject;
import com.demo.api.dto.response.GetUserResponse;
import com.demo.api.dto.response.regrister.ResgristerResponseObject;
import com.demo.api.utils.ObjectMapperUtils;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ResgristerService extends BaseApi {

    public  ResgristerResponseObject postResgrister (ResgristerRequestObject resgristerRequestObject){
        RequestSpecification spec = getRequestBuilderWithoutToken(DemoPath.RESGRISTER_PATH)
                .body(ObjectMapperUtils.convertDTOClassToJSONStringGson(resgristerRequestObject));

        Response response = this.dispatchServiceRequest(spec, Method.POST);
        return ObjectMapperUtils.convertJSONStringToDTOClassByGson(response.body().asString(),ResgristerResponseObject.class);
    }

    public  ResgristerResponseObject postLogin(LoginRequest loginRequest){
        RequestSpecification spec = getRequestBuilderWithoutToken(DemoPath.LOGIN_PATH)
                .body(ObjectMapperUtils.convertDTOClassToJSONStringGson(loginRequest));

        Response response = this.dispatchServiceRequest(spec, Method.POST);
        return ObjectMapperUtils.convertJSONStringToDTOClassByGson(response.body().asString(),ResgristerResponseObject.class);
    }

    public GetUserResponse getAllUser(String token) {
        RequestSpecification spec = getRequestBuilderWithToken(DemoPath.GET_USER_PATH, token);
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        return ObjectMapperUtils.convertJSONStringToDTOClassByGson(response.body().asString(), GetUserResponse.class);
    }

    public GetUserResponse getUser(String token, int userId) {
        Map<String, Object> mapPost = new HashMap<>();
//        String userID = Integer.toString(userId);
        mapPost.put("id", userId);

        String basePath = DemoPath.GET_USER_BY_ID + userId;
        RequestSpecification spec = getRequestBuilder(basePath, token, mapPost);
        Response response = this.dispatchServiceRequest(spec, Method.GET);
        return ObjectMapperUtils.convertJSONStringToDTOClassByGson(response.body().asString(), GetUserResponse.class);
    }

    public void deleteUser(String token, int userId) {
        Map<String, Object> mapPost = new HashMap<>();
        mapPost.put("id", userId);

        String basePath = DemoPath.DELETE_USER_BY_ID + userId;
        RequestSpecification spec = getRequestBuilder(basePath, token, mapPost);
        this.dispatchServiceRequest(spec, Method.DELETE);
    }
}
