package com.zaplink.ZapVerse.controller;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProfileController {

    ProfileService profileService;

    //get profile by ID
    @GetMapping("/profile/{id}")
    public Profile getProfileById(@PathVariable Integer id){
        return profileService.getProfileById(id);
    }

    // get all profiles
    @GetMapping("profiles")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    // create new profile
    @PostMapping("/profile")
    public Profile createNewProfile(@RequestBody Profile profile){
        return profileService.createProfile(profile);
    }

    // update profile
    @PutMapping("/profile/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Integer id, @RequestBody Profile updatedProfile){
        Profile profile = profileService.updateProfile(id, updatedProfile);

        return ResponseEntity.ok(profile);
    }

    // delete profile
    @DeleteMapping("/profile/{id}")
    public void deleteProfile(@PathVariable Integer id){
        profileService.deleteProfile(id);
    }
}
