package codewars;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class MorganStanley2 {

final String lines = "00:01:07,400-234-090\n" +
                     "00:05:01,701-080-080\n" +
                     "00:05:00,400-234-090";
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s");

    public int solution(String lines) {
        String[] lineArr = lines.split("\n");
        final Map<String, Integer> numberToDuration = new HashMap<>();
        Arrays.stream(lineArr).forEach(line -> {
            String duration = line.substring(0, 8);
            LocalTime time = LocalTime.parse(duration, formatter);
            int durationInSeconds = time.toSecondOfDay();
            putIntoMap(numberToDuration, line.substring(9), durationInSeconds);
        });
        final AtomicInteger longestCall = new AtomicInteger(0);
        numberToDuration.forEach((key, value) -> {
            if(value >= longestCall.get()) {
                longestCall.set(value);
            }
        });
        final Set<String> phoneNumbers = new HashSet<>();
        numberToDuration.forEach((key, value) -> {
            if(value == longestCall.get()) {
                phoneNumbers.add(key);
            }
        });
        String promotionPhoneNumber = computePromotionPhoneNumber(phoneNumbers);
        numberToDuration.remove(promotionPhoneNumber);
        final AtomicInteger centsToPay = new AtomicInteger(0);
        numberToDuration.forEach((number, duration) -> {
            if(duration < 300) {
                centsToPay.set(centsToPay.get() + duration * 3);
            } else {
                int minutes = duration / 60;
                if(duration - (minutes * 60) > 0) minutes += 1;
                centsToPay.set(centsToPay.get() + minutes * 150);
            }
        });
        return centsToPay.get();
    }

    private String computePromotionPhoneNumber(Set<String> phoneNumbers) {
        if(phoneNumbers.size() == 1) {
            return phoneNumbers.iterator().next();
        }
        int min = 0;
        String promotionPhoneNumber = phoneNumbers.iterator().next();
        for(String phoneNumber : phoneNumbers) {
            String number = phoneNumber.substring(0, 3) + phoneNumber.substring(4, 7) + phoneNumber.substring(8);
            int numericalValue = Integer.valueOf(number);
            if(numericalValue < min) {
                min = numericalValue;
                promotionPhoneNumber = phoneNumber;
            }
        }
        return promotionPhoneNumber;
    }

    private void putIntoMap(Map<String, Integer> numberToDuration, String key, int durationInSeconds) {
        numberToDuration.compute(key, (s, existingValue) -> {
            if(existingValue == null) existingValue = 0;
            return existingValue + durationInSeconds;
        });
    }

    @Test
    public void test1() {
        int solution = solution(lines);
        assertEquals(900, solution);
    }
}
