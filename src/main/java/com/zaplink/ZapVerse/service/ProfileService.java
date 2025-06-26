package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {

    ProfileRepository profileRepository;

    // getProfileById
    public Profile getProfileById(Integer id){
        return profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("[profile_id: "+ id + " ] doesn't exist")
        );
    }

    // getAllProfiles
    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    // createProfile
    public Profile createProfile(Profile profile){
        return profileRepository.save(profile);
    }

    // updateProfile
    public Profile updateProfile(Integer id, Profile profile){
        Profile existingProfile = profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("[profile_id: "+ id + " ] doesn't exist")
        );

        existingProfile.setEmail(profile.getEmail());
        existingProfile.setFname(profile.getFname());
        existingProfile.setLname(profile.getLname());
        existingProfile.setPassword(profile.getPassword());

        return profileRepository.save(existingProfile);
    }

    // deleteProfile
    public void deleteProfile(Integer id){
        profileRepository.deleteById(id);
    }

}
