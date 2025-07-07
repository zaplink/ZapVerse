package com.zaplink.ZapVerse.service;

import com.zaplink.ZapVerse.model.Profile;
import com.zaplink.ZapVerse.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {

    ProfileRepository profileRepository;
    PasswordEncoder passwordEncoder;

    public Profile getProfileById(Integer id){
        return profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("[profile_id: " + id + " ] doesn't exist")
        );
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.findAll();
    }

    public Profile createProfile(Profile profile){
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Integer id, Profile profile){
        Profile existingProfile = profileRepository.findById(id).orElseThrow(
                () -> new RuntimeException("[profile_id: " + id + " ] doesn't exist")
        );

        existingProfile.setEmail(profile.getEmail());
        existingProfile.setFname(profile.getFname());
        existingProfile.setLname(profile.getLname());
        existingProfile.setPassword(profile.getPassword());
        existingProfile.setAvatar(profile.getAvatar()); // if update needed

        return profileRepository.save(existingProfile);
    }

    public void deleteProfile(Integer id){
        profileRepository.deleteById(id);
    }

    public Profile authenticateUser(String email, String password){
        Optional<Profile> userProfile = profileRepository.findByEmail(email);

        if(userProfile.isPresent()){
            if(userProfile.get().getPassword().equals(password)){
                return userProfile.get();
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        throw new RuntimeException("Invalid email");
    }


    public void registerUser(String email, String password, String firstName, String lastName, String avatar) {
        if (profileRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Profile newUser = new Profile();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setFname(firstName);
        newUser.setLname(lastName);
        newUser.setAvatar(avatar);

        profileRepository.save(newUser);
    }
}
