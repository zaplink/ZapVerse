package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // authenticateUser
    public Profile authenticateUser(String email, String password){
        Optional<Profile> userProfile = profileRepository.findByEmail(email);

        if(userProfile.isPresent()){
            if(userProfile.get().getPassword().equals(password)){
                return userProfile.get();
            }else {
                throw new RuntimeException("Invalid Password");
            }
        }
        throw new RuntimeException("Invalid email");
    }

    public void registerUser(String email, String password) {
    }

    public void registerUser(String email, String password, String firstName, String lastName) {

    }
}
