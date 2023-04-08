//package indv.bozgur.gql.config;
//
//import graphql.schema.DataFetchingEnvironment;
//import indv.bozgur.gql.model.User;
//import indv.bozgur.gql.service.UserService;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserByUsernameDataFetcher implements DataFetcher<User> {
//
//    private final UserService userService;
//
//    public UserByUsernameDataFetcher(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public User get(DataFetchingEnvironment environment) throws Exception {
//        String username = environment.getArgument("username");
//        return userService.getUserByUsername(username);
//    }
//}
