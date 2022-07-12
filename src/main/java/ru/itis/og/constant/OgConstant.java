package ru.itis.og.constant;

import ru.itis.og.model.enumeration.State;

public interface OgConstant {

    // example: String USER_CONTROLLER_PATH = API_PATH + "/users";

    String API_PATH = "/api/v1";
    String SIGN_UP_CONTROLLER_PATH = API_PATH + "/signUp";
    String CONFIRM_CODE_PATH = "/confirm/{code}";

    String SUBSCRIPTION_CONTROLLER_PATH = API_PATH + "/subscriptions";
    String FOLLOWERS_PATH = "/followers";
    String FOLLOWINGS_PATH = "/followings";

    String POST_CONTROLLER_PATH = API_PATH + "/posts";

    String LINK_CONTROLLER_PATH = API_PATH + "/links";

    String DESCRIPTION_CONTROLLER_PATH = API_PATH + "/accounts/{account-id}/description";

    String PRODUCT_CONTROLLER_PATH = API_PATH + "/products";

    String QUESTION_CONTROLLER_PATH = API_PATH + "/questions";
    String QUESTION_ANSWER_CONTROLLER_PATH = "/{question-id}";
    String QUESTION_GET_ANSWERED_CONTROLLER_PATH = "/get-answered";
    String QUESTION_GET_NOT_ANSWERED_CONTROLLER_PATH = "/get-not-answered";

    String FILE_CONTROLLER_PATH = API_PATH + "/files";
    String GET_FILE_PATH = "/{filename:.+}";

    int PAGEABLE_DEFAULT_SIZE = 5;
    int TIME_TO_EDIT_POST = 60 * 60 * 24; // 24 hours

    State DEFAULT_STATE = State.PUBLISHED;
}
