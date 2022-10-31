package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataAdder implements ApplicationListener<ApplicationReadyEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Add initial data");
        Author author1 = new Author(1, "Author 1");

        authorRepository.save(author1);
        bookRepository.save(new Book(1, "Book 1", author1));
    }
}
