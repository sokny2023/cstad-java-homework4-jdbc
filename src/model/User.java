package model;

import lombok.*;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class User {
    private Integer userId;
    private String userUuid;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Boolean isDeleted;
    private Boolean isVerified;

    public User(Integer userId, String userUuid, String userName, String userEmail, String userPassword, Boolean isDeleted, Boolean isVerified) {
        this.userId = userId;
        this.userUuid = userUuid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isDeleted = isDeleted;
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userUuid='" + userUuid + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isDeleted=" + isDeleted +
                ", isVerified=" + isVerified +
                '}';
    }
}
