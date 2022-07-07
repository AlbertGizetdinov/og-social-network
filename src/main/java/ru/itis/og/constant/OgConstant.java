package ru.itis.og.constant;

public interface OgConstant {

    // example: String USER_CONTROLLER_PATH = API_PATH + "/users";

    String API_PATH = "/api/v1";
    String SUBSCRIPTION_CONTROLLER_PATH = API_PATH + "/subscriptions";
    String FOLLOWERS_PATH = "/followers";
    String FOLLOWINGS_PATH = "/followings";

    int PAGEABLE_DEFAULT_SIZE = 5;

}
