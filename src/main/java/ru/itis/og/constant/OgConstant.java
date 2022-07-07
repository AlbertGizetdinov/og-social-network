package ru.itis.og.constant;

public interface OgConstant {

    // example: String USER_CONTROLLER_PATH = API_PATH + "/users";

    String API_PATH = "/api/v1";
    String SIGN_UP_CONTROLLER_PATH = API_PATH + "/signUp";
    String CONFIRM_CODE_PATH = "/confirm/{code}";

    String SUBSCRIPTION_CONTROLLER_PATH = API_PATH + "/subscriptions";
    String FOLLOWERS_PATH = "/followers";
    String FOLLOWINGS_PATH = "/followings";

    String POSTS_CONTROLLER_PATH = API_PATH + "/posts";

    int PAGEABLE_DEFAULT_SIZE = 5;
    int TIME_TO_EDIT_POST = 60 * 60 * 24; // 24 hours
}
