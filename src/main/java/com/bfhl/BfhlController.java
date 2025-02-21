package com.bfhl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {
    @GetMapping
    public Map<String, Object> getOperationCode(){
        Map<String, Object> response = new HashMap<>();
        response.put("oberation_code", 1);
        return response;
    }

    @PostMapping
    public Map<String, Object> processRequest(
            @RequestBody 
            Map<String, List<String>> input
        ){
        Map<String, Object> response = new HashMap<>();
        List<String> data = input.getOrDefault("data", new ArrayList<>());

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        String highestAlphabet = null;

        for(String item: data){
            if(item.matches("\\d+"))    numbers.add(item);
            else if(item.matches("[a-zA-Z]")){
                alphabets.add(item);
                if (highestAlphabet == null || item.compareToIgnoreCase(highestAlphabet) > 0) {
                    highestAlphabet = item;
                }
            }
        }

        response.put("is_success", true);
        response.put("user_id", "your_name_ddmmyyyy");
        response.put("email", "your_email@college.com");
        response.put("roll_number", "your_roll_number");
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);
        response.put("highest_alphabet", highestAlphabet != null ? Collections.singletonList(highestAlphabet) : Collections.emptyList());


        return response;
    }
}
