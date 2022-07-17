package net.collabstack.app.auth.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserResponse {

    private Integer id;
    private String imageUrl;
    private String company;
    private String githubUrl;
    private String name;
    private String email;

    public static GithubUserResponse from(final Map<String, Object> users) {
        GithubUserResponse response = new GithubUserResponse();
        response.setId((int) users.get("id"));
        response.setImageUrl((String) users.get("avatar_url"));
        response.setCompany((String) users.get("company"));
        response.setGithubUrl((String) users.get("html_url"));
        response.setName((String) users.get("name"));
        response.setEmail((String) users.get("email"));
        return response;
    }

}
