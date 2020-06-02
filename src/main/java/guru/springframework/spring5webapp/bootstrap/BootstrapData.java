package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher dan = new Publisher("Daniel Varga", "Whatevs street 2", "Little Town Called" +
                "None of Your Goddamn Business", "Flyover", 2342);
        publisherRepository.save(dan);

        System.out.println("Number of Publishers: " + publisherRepository.count());


        Author ernest = new Author("Ernest", "Hemingway");
        Book forWhomTheBellTolls = new Book("For Whom The Bell Tolls", "123123");

        ernest.getBooks().add(forWhomTheBellTolls);
        forWhomTheBellTolls.getAuthors().add(ernest);
        forWhomTheBellTolls.setPublisher(dan);
        dan.getBooks().add(forWhomTheBellTolls);

        authorRepository.save(ernest);
        bookRepository.save(forWhomTheBellTolls);
        publisherRepository.save(dan);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "32423432423");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(dan);
        dan.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(dan);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of books: " + dan.getBooks().size());
    }
}
