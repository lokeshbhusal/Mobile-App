Mobile Application Development Report

Introduction
This report provides a detailed overview of a mobile application designed for the CSE5007 course. The primary goal of the app is to assist users in discovering points of interest (POIs) by leveraging a map-based interface. Enhanced with artificial intelligence, the application aims to offer personalized experiences, making it easier for users to find locations that align with their interests.

Key Features

Map Integration: Utilizes Google Maps API to display POIs on a map, allowing users to visually navigate different locations.
User Authentication: Ensures that users can securely log in and manage their accounts.
POI Submission and Ratings: Users can add new POIs along with descriptions and ratings, contributing to the richness of the community-driven data.
Personalized Recommendations: AI algorithms analyze user preferences and behaviors to suggest POIs that might be of interest.
Firebase Products Used
The application incorporates several Firebase products to streamline development and enhance functionality:

Firebase Firestore: Serves as the backend for storing and retrieving data such as user details and POI information. It allows for real-time updates across user devices.
Firebase Authentication: Manages user registration, login, and session management. It supports various authentication mechanisms, including email and password, and social media logins, enhancing user convenience and security.
Firebase Storage: Used for uploading and retrieving images related to POIs. Users can upload pictures of locations, which are stored in Firebase and can be retrieved efficiently to display within the app.
Detailed Firebase Integration

User Authentication and Management:
Registration and Login: Firebase Authentication is used to handle user sign-ups and logins. When a user registers, their credentials are stored securely in Firebase, and the same credentials are validated during the login process.
Session Handling: Firebase also manages sessions to keep users logged in across sessions, improving the user experience.
Data Handling with Firestore:
Storing POI Data: When a user submits a new POI, the details, including location coordinates, descriptions, and ratings, are stored in Firestore.
Retrieving POI Data: Firestore queries are used to fetch POI data to display on maps or in lists, depending on user queries and filters.
Image Management with Firebase Storage:
Uploading Images: Users can upload images of POIs, which are stored in Firebase Storage. This integration ensures that images are uploaded efficiently and securely.
Retrieving Images: When displaying POI details, images associated with those points are retrieved from Firebase Storage to provide a visual context.
Architecture
The architecture of the app is structured to support robust scalability and efficient data flow:

Activities: Defined for different screens like login, signup, map view, and POI details, each activity handles specific user interactions.
Services: Background services handle data synchronization and AI-driven recommendations.
Source Code Analysis
Source code is organized into packages:

Activities Package: Contains all activity files managing user interfaces.
Helpers Package: Includes utility classes that assist in handling Firebase operations, image processing, and data validation.
Models Package: Defines data models for users, POIs, and images.
Next Steps

Enhancing AI Features: Improving AI algorithms for more accurate personalization based on user behavior and feedback.
UI/UX Improvements: Redesigning user interfaces for better usability and modern aesthetics.
Database Expansion: Adding more attributes to the POI data model to include features like user comments, historical visit data, and seasonal popularity.
