# Movie Tracker App with Kotlin and Jetpack Compose

A mobile application to track movies you have watched and manage your favorite movies.

## Features
- View a list of movies
- Add or remove movies from favorites
- View movie details
- Manage user profile

## Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern, which helps in separating the concerns of the application and makes the code more maintainable and testable.

### Model
- Represents the data layer of the application.
- Responsible for handling the business logic and communication with the data sources (e.g., local database, network).

### View
- Represents the UI layer of the application.
- Displays the data and interacts with the user.
- Composed of Jetpack Compose functions.

### ViewModel
- Acts as a bridge between the Model and the View.
- Holds the UI-related data and exposes it to the View.
- Handles the user interactions and updates the Model accordingly.

### Dependency Injection
- The project uses **Dagger Hilt** for dependency injection, which helps in managing the dependencies and provides a clean way to inject them.

### Navigation
- The project uses **Jetpack Navigation Component** for handling navigation between different screens.
- The navigation graph is defined to manage the navigation flow.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/iwatchedthis.git
    cd iwatchedthis
    ```

2. Open the project in Android Studio.

3. Build the project:
    - Click on `Build` \> `Make Project` or press `Ctrl+F9`.

4. Run the project:
    - Click on `Run` \> `Run 'app'` or press `Shift+F10`.

## The App Screens

### Home Screen
- The home screen displays a list of movies and searchbar.
- Use the bottom navigation bar to switch between different sections.
<img src="https://github.com/user-attachments/assets/58578b3e-550a-4611-a886-69273b7e0e74" alt="Main Screen" width="25%" height="25%"/>


### Comments Screen
- You can see your comments for movies.
- You can delete comments on the screen.
<img src="https://github.com/user-attachments/assets/a43b5a47-1501-4f7d-9590-223777115baa" alt="Comments Screen" width="25%" height="25%"/>


### Movie Details
- Click on a movie to view its details.
- Add or remove the movie from your favorites.
<img src="https://github.com/user-attachments/assets/38687896-a464-4048-b351-a0bc0322937e" alt="MovieDetails Screen" width="25%" height="25%"/>


### Profile
- Manage your user profile.
- View and edit your favorite and watched movies.
<img src="https://github.com/user-attachments/assets/78a03ddf-b734-42aa-b8f5-964540ef94c6" alt="Profile Screen" width="25%" height="25%"/>

## Contributing

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature/your-feature-name
    ```
3. Make your changes.
4. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
5. Push to the branch:
    ```sh
    git push origin feature/your-feature-name
    ```
6. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
