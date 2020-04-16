package br.com.lucianoheredia.commonword;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

import static java.util.Comparator.comparingInt;

@SpringBootApplication
public class CommonWordApplication {

    public static void main(String[] args) {
		String paragraph = "a,a,a,a,b,b,b,c,c";
		String[] banned = {"a"};

		System.out.println(mostCommonWord(paragraph, banned));
    }


    public static String mostCommonWord(String paragraph, String[] banned) {
        StringBuilder result = new StringBuilder();
        Map<String, Integer> map = new HashMap();
        String[] split = paragraph.replaceAll("[!?',;.]"," ").toLowerCase().split(" ");
        Set<String> bannedList = new HashSet<>(Arrays.asList(banned));

        Arrays.stream(split).filter(str -> !bannedList.contains(str))
                .forEachOrdered(str -> map.put(str, map.getOrDefault(str, 0) + 1));

         map.remove("");
         map.entrySet().stream().max(comparingInt(Map.Entry::getValue)).ifPresent(e-> result.append(e.getKey()));

        return result.toString();
    }
}
