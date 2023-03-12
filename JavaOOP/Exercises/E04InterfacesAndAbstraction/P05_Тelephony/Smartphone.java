package E04InterfacesAndAbstraction.P05_Тelephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Smartphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder out = new StringBuilder();
        Pattern pattern = Pattern.compile("([0-9])");

        out.append(urls.stream()
                .map(e -> {
                    Matcher matcher = pattern.matcher(e);
                    if (matcher.find()) {
                        return "Invalid URL!";
                    } else {
                        return "Browsing: " + e + "!";
                    }
                })
                .collect(Collectors.joining(System.lineSeparator())));

        return out.toString();

    }

    @Override
    public String call() {
        StringBuilder out = new StringBuilder();
        Pattern pattern = Pattern.compile("([^0-9])");

        out.append(numbers.stream()
                .map(e -> {
                    Matcher matcher = pattern.matcher(e);
                    if (matcher.find() || e.isEmpty()) {
                        return "Invalid number!";
                    } else {
                        return "Calling... " + e;
                    }
                })
                .collect(Collectors.joining(System.lineSeparator())));

        return out.toString();
    }

}
