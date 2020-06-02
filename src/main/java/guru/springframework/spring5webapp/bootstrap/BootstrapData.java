package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author ernest = new Author("Ernest", "Hemingway");
        Book forWhomTheBellTolls = new Book("For Whom The Bell Tolls", "123123");
        ernest.getBooks().add(forWhomTheBellTolls);
        forWhomTheBellTolls.getAuthors().add(ernest);

        authorRepository.save(ernest);
        bookRepository.save(forWhomTheBellTolls);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "32423432423");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());

        Publisher dan = new Publisher("Daniel Varga", "Whatevs street 2", "Little Town Called" +
                "None of Your Goddamn Business", "Flyover", 2342);
    }
}
