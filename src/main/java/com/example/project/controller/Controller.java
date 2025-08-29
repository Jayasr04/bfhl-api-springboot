package com.example.project.controller;
import com.example.project.dto.InputRequest;
import com.example.project.dto.OutputResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bfhl")
public class Controller {

    @PostMapping
    public OutputResponse processData(@RequestBody InputRequest request) {
        OutputResponse response = new OutputResponse();

        try {
            List<String> data = request.getData();

            List<String> evenNumbers = new ArrayList<>();
            List<String> oddNumbers = new ArrayList<>();
            List<String> alphabets = new ArrayList<>();
            List<String> specialChars = new ArrayList<>();
            int sum = 0;

            for (String item : data) {
                if (item.matches("^-?\\d+$")) {  // number check
                    int num = Integer.parseInt(item);
                    if (num % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                    sum += num;
                } else if (item.matches("^[a-zA-Z]+$")) { // alphabet check
                    alphabets.add(item.toUpperCase());
                } else { // special character
                    specialChars.add(item);
                }
            }

            // concat string: reverse alphabets, alternating caps
            String allAlpha = alphabets.stream().collect(Collectors.joining(""));
            String reversed = new StringBuilder(allAlpha).reverse().toString();
            StringBuilder altCaps = new StringBuilder();
            for (int i = 0; i < reversed.length(); i++) {
                char ch = reversed.charAt(i);
                if (i % 2 == 0) {
                    altCaps.append(Character.toUpperCase(ch));
                } else {
                    altCaps.append(Character.toLowerCase(ch));
                }
            }

            response.setIs_success(true);
            response.setUser_id("jayasri_jayapal_06062004");
            response.setEmail("jayasri@gmail.com");
            response.setRoll_number("22BCE3352");
            response.setEven_numbers(evenNumbers);
            response.setOdd_numbers(oddNumbers);
            response.setAlphabets(alphabets);
            response.setSpecial_characters(specialChars);
            response.setSum(String.valueOf(sum));
            response.setConcat_string(altCaps.toString());

        } catch (Exception e) {
            response.setIs_success(false);
        }

        return response;
    }
}