package indv.bozgur.gql.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import indv.bozgur.gql.model.Role;
import indv.bozgur.gql.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
class UserControllerTest {

  @Autowired GraphQlTester graphQlTester;

  @BeforeEach
  void setUp() {
    createUser(new User("berke", "berke@mail.com", Role.USER));
    createUser(new User("can", "can@mail.com", Role.USER));
    createUser(new User("ozgur", "ozgur@mail.com", Role.ADMIN));
  }

  @Test
  void when_getAllUsers_should_return_userList() {

    // language=graphql
    String query =
        """
        query {
          getAllUsers{
            id
            username
            role
            created
            updated
          }
        }
        """;

    graphQlTester.document(query).execute().path("getAllUsers").entityList(User.class).hasSize(3);
  }

  @Test
  void when_createUser_should_createNewUserAndReturnUser() {
    String mutation =
        """
        mutation {
          createUser(userRequest: {username: "bozgur", mail: "bozgur@mail.com", role: ADMIN}) {
            id
            username
            mail
            role
            created
            updated
          }
        }
        """;
    graphQlTester
        .document(mutation)
        .execute()
        .path("createUser")
        .entity(User.class)
        .satisfies(
            x -> {
              assertEquals("bozgur", x.getUsername());
              assertEquals("bozgur@mail.com", x.getMail());
            });
  }

  void createUser(User user) {
    String mutation =
        """
        mutation {
          createUser(userRequest: {username: "%s", mail: "%s", role: %s}) {
            id
            username
            role
            created
            updated
          }
        }
        """
            .formatted(user.getUsername(), user.getMail(), user.getRole());

    graphQlTester.document(mutation).execute().path("createUser");
  }
}
