package com.example.demo;

import lombok.Data;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataFetcher {

    String getFirstAuthorName() {
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder().url("localhost:8080/graphql").build();
        // Java 15 would allow better formatted text blocks
        String document = "{"+
                "   authors {" +
                "       name" +
                "   }" +
                "}";
        return graphQlClient.document(document).retrieve("authors[0].name").toEntity(String.class).block();
    }





    List<String> getAuthorNames() {
        HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder().url("localhost:8080/graphql").build();
        // Java 15 would allow better formatted text blocks
        String document = "{"+
                "   authors {" +
                "       name" +
                "   }" +
                "}";
        return graphQlClient.document(document).retrieve("authors").toEntityList(AuthorName.class).block()
                .stream().map(AuthorName::getName).collect(Collectors.toList());
    }
}
// Java 14: record AuthorName(String name)
@Data
class AuthorName {
    String name;
}