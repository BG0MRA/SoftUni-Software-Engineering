package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class BookServiceImpl implements BookService {

    private static String FILE_PATH = "src/main/resources/files/json/books.json";

    private BookRepository bookRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, Gson gson, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        List<BookImportDto> books = Arrays.stream(this.gson.fromJson(readBooksFromFile(), BookImportDto[].class)).collect(Collectors.toList());

        for (BookImportDto book : books) {
            stringBuilder.append(System.lineSeparator());

            if (this.bookRepository.findFirstByTitle(book.getTitle()).isPresent() ||
                    !validationUtils.isValid(book)) {
                stringBuilder.append(String.format(INVALID_FORMAT, BOOK));
                continue;
            }

            //Successfully imported book F. Scott Fitzgerald - The Great Gatsby
            this.bookRepository.save(this.modelMapper.map(book, Book.class));

            stringBuilder.append(String.format(
                    SUCCESSFUL_FORMAT,
                    BOOK,
                    book.getAuthor(),
                    book.getTitle()
                    ));
        }


        return stringBuilder.toString().trim();
    }
}
