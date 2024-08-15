package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<User> listUsers() {
        return userDao.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userDao.getUserById(id);
    }

    // POST mapping to upload a profile picture
    @PostMapping("/{id}/profile-picture")
    public ResponseEntity<User> uploadProfilePicture(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            // You would store the file and get a URL for the stored image.
            // For simplicity, assuming a method exists in your DAO to handle this.
            String profilePictureUrl = saveProfilePicture(file); // Implement this method
            User user = userDao.getUserById(id);
            user.setProfilePicture(profilePictureUrl);
            userDao.updateUser(user);  // Assuming this method exists to update the user
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // PUT mapping to update user information
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User existingUser = userDao.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        updatedUser.setId(id); // Ensure the ID remains the same
        userDao.updateUser(updatedUser);  // Assuming updateUser method exists
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE mapping to remove a user's profile picture
    @DeleteMapping("/{id}/profile-picture")
    public ResponseEntity<Void> deleteProfilePicture(@PathVariable int id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setProfilePicture(null);
        userDao.updateUser(user);  // Assuming updateUser method exists
        return ResponseEntity.noContent().build();
    }

    private String saveProfilePicture(MultipartFile file) throws IOException {
        // Implement the logic to save the file and return the URL.
        // For example, you might store the file in the filesystem or a cloud service.
        return "URL_to_saved_image";
    }
}
