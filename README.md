Smart Pantry Manager
====================

Smart Pantry Manager is a Java Android application designed to help
reduce food waste by allowing users to track ingredients they already
have and find recipes that can be prepared strictly from those
ingredients.

Project
-------
Module: Mobile App Development 700
Platform: Android
Language: Java
IDE: Android Studio

Features
--------
- Add, view, edit and delete pantry ingredients
- Store ingredient quantity, unit and optional expiry date
- Persistent local storage using SQLite
- 18 pre-loaded recipes
- Strict recipe matching based on ingredient availability and quantity
- Compatible unit conversion such as kg/g and L/ml
- Simple singular/plural ingredient matching
- Recipe detail and preparation instructions
- No-match feedback when no recipe can currently be prepared
- Profile name storage using SharedPreferences
- RecyclerView-based pantry and recipe lists
- Input validation and user feedback

Database
--------
The application uses SQLite for local data persistence.
SQLite was chosen because the application primarily manages a user's
local pantry and recipe data, and it provides persistent on-device
storage without requiring an external server.

The database contains three main tables:

- pantry_items
- recipes
- recipe_ingredients

Setup and Running
-----------------
1. Open the project in Android Studio.
2. Allow Gradle to complete synchronization.
3. Connect an Android device or start an Android emulator.
4. Run the application using Android Studio.

Repository
----------
GitHub:
https://github.com/ProvenObject/Smart-Pantry-Manager
