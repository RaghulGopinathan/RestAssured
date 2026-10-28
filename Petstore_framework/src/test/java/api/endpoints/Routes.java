package api.endpoints;

public class Routes {
        public static String BaseURL = "https://petstore.swagger.io/v2";


        //User module urls
        public static String getURL = BaseURL+"/user/{username}";
        public static String postURL = BaseURL+"/user";
        public static String putURL = BaseURL+"/user/{username}";
        public static String deleteURL = BaseURL +"/user/{username}";
}
