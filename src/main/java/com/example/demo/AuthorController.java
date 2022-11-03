package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @QueryMapping
    public Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }

    @MutationMapping
    Author addAuthor(@Argument AuthorInput author) {
        Author newAuthor = new Author(author.getId(), author.getName());
        return authorRepository.save(newAuthor);
    }

    // Java 14
    // record AuthorInput(String id, String name);
}
@ProjectedPayload
interface AuthorInput {
    Long getId();
    String getName();
}