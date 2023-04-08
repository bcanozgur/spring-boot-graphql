package indv.bozgur.gql.model;

import lombok.Data;

@Data
public class UserRequest {
  private Long id;
  private String username;
  private String mail;
  private Role role;
}
