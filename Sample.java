import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sample {
    public static void main(final String[] args) {
        final String[] words = new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat",
                "ratcatdogcat"};
        final List<String> wordList = Arrays.asList(words);

        wordList.stream()
                .filter(e -> !(null == e))
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);
    }
}
