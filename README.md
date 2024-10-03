# TalentHive

Thrive is a unique platform that enables users to exchange skills through a community-driven "Skill Swap" system. The app allows users to create an account, list their available skills, and search for others who offer skills they would like to learn. By providing a smooth user experience with secure authentication and real-time interaction, Thrive facilitates a growing network of knowledge-sharing among users.

## Key Features

- Skill Swap: Users can list their skills (such as "Guitar," "Coding," or "Photography") and browse other users' profiles to find skills they'd like to learn.
- User Registration and Authentication: Secure email and password-based registration using Firebase Authentication.
- Username Validation: During registration, Thrive checks if a username is unique, ensuring that users can create personalized and distinct profiles.
- Profile Creation: Each user can create a profile with their email, username, and a list of skills they want to offer.
- Real-time Data Storage: User profiles, including skills, are stored in Firebase Realtime Database, enabling dynamic interactions and live data updates.
- Secure and Intuitive UI: Features like password visibility toggle and easy navigation between screens make for a seamless user experience.

## How It Works

### 1. Registering a New User:
   - Users enter their username, email, and password to register.
   - The app checks if the username is already taken. If it's unique, the user proceeds with registration.
   - Once registered, their profile is created in Firebase Realtime Database with their username, email, and the option to add or modify their skills.
   - The password is securely handled via Firebase Authentication (not stored in the database).

### 2. Creating a Profile:
   - Users can list skills they possess and are willing to offer (e.g., "Photography," "Guitar," "Cooking").
   - Users can also list skills they want to learn, allowing others in the community to reach out for potential swaps.

### 3. Finding Skill Matches:
   - Users can search through profiles to find others who offer skills they want to learn.
   - Once they find a match, they can initiate a skill swap — offering to teach their own skill in exchange for learning the desired one.

### 4. Real-time Updates:
   - All user data, including skills, is stored in Firebase Realtime Database. Changes made to user profiles, skill listings, or other updates are reflected instantly across the app.

## Firebase Setup

To set up Firebase for Thrive, follow these steps:

1. Create a Firebase Project:
   - Visit the [Firebase Console](https://console.firebase.google.com/) and create a new project.
   - Add an Android app to your Firebase project and download the `google-services.json` file.
   
2. Enable Firebase Authentication:
   - Under the Authentication section in Firebase Console, enable Email/Password Authentication.

3. Setup Firebase Realtime Database:
   - In the Realtime Database section, create a new database and set rules to restrict read/write access to authenticated users:
     ```json
     {
       "rules": {
         ".read": "auth != null",
         ".write": "auth != null"
       }
     }
     ```

4. Add Firebase Dependencies:
   Add the following dependencies in your `build.gradle` file:
   ```groovy
   implementation platform('com.google.firebase:firebase-bom:32.0.0')
   implementation 'com.google.firebase:firebase-auth'
   implementation 'com.google.firebase:firebase-database'
   ```

## Project Structure

```bash
app/
└── src/
    └── main/
        ├── java/
        │   └── com/example/thrive/
        │       ├── Register.kt        # Handles user registration and profile creation
        │       ├── Login.kt           # Handles user login
        │       ├── ProfileActivity.kt # Displays and manages user profiles and skills
        │       ├── SkillSwapActivity.kt # Facilitates the skill swap process between users
        └── res/
            ├── layout/
            │   ├── activity_register.xml  # XML layout for registration screen
            │   ├── activity_login.xml     # XML layout for login screen
            │   ├── activity_profile.xml   # XML layout for user profile screen
            │   ├── activity_skill_swap.xml# XML layout for skill swap
            └── drawable/
                ├── eye.png                # Image for password visibility on
                ├── baseline_remove_red_eye_24.xml  # Image for password visibility off
```

## Screens

1. Registration Screen: 
   - Enter username, email, and password.
   - Check username uniqueness.
   - Register using Firebase Authentication and create a profile in Firebase Realtime Database.

2. Login Screen:
   - User login via email and password.

3. Profile Screen:
   - Users can add skills they possess or skills they want to learn.
   - View their username and email.

4. Skill Swap Screen:
   - Users can search for others offering skills they'd like to learn.
   - Initiate and manage skill swap requests.

## Usage

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repository/thrive.git
   ```
2. Open the project in Android Studio.

3. Configure Firebase:
   - Add your `google-services.json` file to the project as per Firebase setup.

4. Run the app on your Android device or emulator.

## Future Enhancements

- Add chat functionality to allow users to communicate during a skill swap.
- Implement a rating system where users can leave feedback after a skill exchange.
- Integrate a notification system for new skill swap offers or messages.
- Add more authentication methods (Google, Facebook, etc.).
- Improve the search and match algorithms for finding suitable skill swap partners.

