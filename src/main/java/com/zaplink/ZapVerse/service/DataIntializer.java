package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataIntializer {

    ProfileRepository profileRepository;

    @PostConstruct
    public void init(){

        Profile profile1 = new Profile("john@gmail.com", "abc1234", "John", "Doe");
        Profile profile2 = new Profile("kal@gmail.com", "kl324", "Kal", "Stark");

        profileRepository.save(profile1);
        profileRepository.save(profile2);

        System.out.println("\nData saved to profile\n");
    }
}
