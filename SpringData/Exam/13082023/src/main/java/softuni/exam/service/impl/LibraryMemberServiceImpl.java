package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.LibraryMemberImportDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    private static String FILE_PATH = "src/main/resources/files/json/library-members.json";

    private LibraryMemberRepository libraryMemberRepository;
    private Gson gson;
    private ModelMapper modelMapper;

    private ValidationUtils validationUtils;

    @Autowired
    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, Gson gson, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        List<LibraryMemberImportDto> libraryMembers = Arrays.stream(this.gson.fromJson(readLibraryMembersFileContent(), LibraryMemberImportDto[].class)).collect(Collectors.toList());

        for (LibraryMemberImportDto libraryMember : libraryMembers) {
            stringBuilder.append(System.lineSeparator());

            if (this.libraryMemberRepository.findFirstByPhoneNumber(libraryMember.getPhoneNumber()).isPresent() ||
                    !validationUtils.isValid(libraryMember)) {
                stringBuilder.append(String.format(INVALID_FORMAT, LIBRARY_MEMBER));
                continue;
            }

            this.libraryMemberRepository.save(this.modelMapper.map(libraryMember, LibraryMember.class));

            //Successfully imported library member John - Doe
            stringBuilder.append(String.format(
                    SUCCESSFUL_FORMAT,
                    LIBRARY_MEMBER,
                    libraryMember.getFirstName(),
                    libraryMember.getLastName()
            ));

        }

        return stringBuilder.toString().trim();
    }
}
