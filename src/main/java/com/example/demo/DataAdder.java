package com.example.demo;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataAdder implements ApplicationListener<ApplicationReadyEvent> {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final DataFetcher dataFetcher;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Add initial data");
        Author author1 = new Author(1, "Lewis Carroll");
        Author author2 = new Author(2, "George Orwell");

        authorRepository.save(author1);
        authorRepository.save(author2);

        bookRepository.save(new Book(1, "Alice's Adventure in Wonderland", author1));
        bookRepository.save(new Book(2, "Through the Looking-Glass", author1));

        bookRepository.save(new Book(3, "Nineteen Eighty-Four", author2));
        bookRepository.save(new Book(4, "Animal Farm", author2));
        bookRepository.save(new Book(5, "Coming Up for Air", author2));
        bookRepository.save(new Book(6, "Keep the Aspidistra Flying", author2));

        // Example get calls
        log.info("First author id: " + dataFetcher.getFirstAuthorName());
        log.info("Author ids: " + String.join(", ", dataFetcher.getAuthorNames()));
    }
}
