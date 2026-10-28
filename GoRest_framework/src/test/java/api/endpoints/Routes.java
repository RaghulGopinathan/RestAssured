package api.endpoints;

public class Routes {

    public static String BASE_URL = "https://gorest.co.in/public/v2";
    public static String BEARER_TOKEN = "d3d30b9d9daa35e6c80ef8b8e57a88bec41bca3b4ffad585012285fc57dfabbf";
    //Users module
    public static String USER_GET_URL = BASE_URL + "/users/{userId}";
    public static String USER_POST_URL = BASE_URL + "/users";
    public static String USER_PUT_URL = BASE_URL + "/users/{userId}";
    public static String USER_DELETE_URL = BASE_URL + "/users/{userId}";

}
