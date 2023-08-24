package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordDto;
import softuni.exam.models.dto.BorrowingRecordImportDto;
import softuni.exam.models.dto.BorrowingRecordWrapperDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.entity.enums.Genre;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private static String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private BorrowingRecordRepository borrowingRecordRepository;
    private BookRepository bookRepository;
    private LibraryMemberRepository libraryMemberRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;

    @Autowired
    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        List<BorrowingRecordImportDto> borrowingRecords = this.xmlParser.fromFile(Path.of(FILE_PATH).toFile(), BorrowingRecordWrapperDto.class).getBorrowingRecords();

        for (BorrowingRecordImportDto borrowingRecord : borrowingRecords) {
            stringBuilder.append(System.lineSeparator());

            Optional<Book> book = this.bookRepository.findFirstByTitle(borrowingRecord.getBook().getTitle());
            Optional<LibraryMember> member = this.libraryMemberRepository.findById(borrowingRecord.getMember().getId());

            if (book.isEmpty() || member.isEmpty() || !validationUtils.isValid(borrowingRecord)) {
                stringBuilder.append(String.format(INVALID_FORMAT, BORROWING_RECORD));
                continue;
            }

            BorrowingRecord entity = this.modelMapper.map(borrowingRecord, BorrowingRecord.class);
            entity.setBook(book.get());
            entity.setLibraryMember(member.get());

            this.borrowingRecordRepository.save(entity);

            //Successfully imported borrowing record The Lord of the Rings - 2020-01-13
            stringBuilder.append(String.format(
                    SUCCESSFUL_FORMAT,
                    BORROWING_RECORD,
                    book.get().getTitle(),
                    borrowingRecord.getBorrowDate()
            ));


        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {
        return this.borrowingRecordRepository.findAllByBorrowDateBeforeAndBookGenreOrderByBorrowDateDesc(LocalDate.of(2021, 9, 10), Genre.SCIENCE_FICTION)
                .stream()
                .map(borrowingRecord -> this.modelMapper.map(borrowingRecord, BorrowingRecordDto.class))
                .map(BorrowingRecordDto::toString)
                .collect(Collectors.joining())
                .trim();
    }
}
