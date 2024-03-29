package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.service.AstronomerService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// TODO: Implement all methods
@Service
public class AstronomerServiceImpl implements AstronomerService {
    private static String FILE_PATH = "src/main/resources/files/xml/astronomers.xml";

    private AstronomerRepository astronomerRepository;

    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository) {
        this.astronomerRepository = astronomerRepository;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        return null;
    }
}
