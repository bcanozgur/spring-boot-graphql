package indv.bozgur.gql.controller;

import indv.bozgur.gql.model.User;
import indv.bozgur.gql.model.UserRequest;
import indv.bozgur.gql.service.UserService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @QueryMapping
  List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @QueryMapping
  User getUserById(@Argument Long id) {
    return userService.getUserById(id);
  }

  @MutationMapping
  User createUser(@Argument UserRequest userRequest) {
    return userService.createUser(userRequest);
  }

  @MutationMapping
  User updateUser(@Argument UserRequest userRequest) {
    return userService.updateUser(userRequest);
  }

  @MutationMapping
  Boolean deleteUser(@Argument Long id) {
    userService.deleteUser(id);
    return true;
  }
}
