# Music Player with DeepLink Integration  

- **Mobile Apps II Assignment 8**  

## Project Description  

This project is an Android application designed to implement DeepLink functionality and media playback features.  
The app allows users to navigate between the music player interface via a web link using DeepLink and interact with audio playback through play, pause, and navigation buttons.  
The project highlights modern Android development practices, integrating tools such as **Media3 ExoPlayer** for audio playback and **DeepLink protocols** for seamless external navigation.  

### Key Features:  

- **DeepLink Integration**: Allows users to navigate directly into the Music Player app screen via a web link.  
- **Playback Controls**: Users can play, pause, skip, and navigate back between tracks.  
- **Music Playback**: Integrated audio playback with ExoPlayer for seamless track streaming.  
- **Web Link Navigation**: Clicking a DeepLink button opens the music player screen through configured deep linking settings.  

### Screens:  

- **Home Screen**: The initial screen of the app that allows users to navigate to the Music Player Screen using a button.  
  - **Navigate to Music Player Button**: Clicking this takes users to the Music Player screen.  

- **Music Player Screen**: Allows users to control music playback using intuitive buttons and provides navigation back to the Home Screen.  
  - **Previous Button**: Navigates to the previous track in the playlist.  
  - **Play/Pause Button**: Toggles the playback state of the currently selected track.  
  - **Next Button**: Skips to the next song in the playlist.  
  - **Back Arrow Icon**: Clicking this icon stops the music playback and navigates back to the Home Screen.  

### Key Functionalities:  

1. **DeepLink Configuration**: Set up and verified using `assetlinks.json` and GitHub Pages for proper navigation.  
2. **Web Button Linking**: A web page provides the DeepLink button to navigate to the app.  
3. **Audio Playback**: Integrated five downloaded songs for user interaction via **ExoPlayer Media3**.  
4. **Playback Buttons**: Users can control playback state via the Play, Pause, Previous, and Next buttons.  

---

## How to Copy and Use the Project from GitHub  

To set up and run the project locally, follow these steps:  

### 1. Clone the Repository:
Copy the project repository from GitHub.

### 2. Open the Project in Android Studio:  
After cloning, open **Android Studio**, and click on **Open an Existing Project**. Navigate to the location where you cloned the repository and select it.  

### 3. Set Up the Emulator:  
In Android Studio, configure the **Android Emulator** to test the application:  
- Go to **Tools > AVD Manager** and select or configure an emulator.  
- Alternatively, connect a physical Android device to test the app.  

### 4. Build and Run the Application:  
Click on the **green Play** button in Android Studio to build and launch the application on the emulator.  

### 5. Open the Web Page with the DeepLink Button:  
To test the DeepLink functionality, visit the following URL: [DeepLink Web Page](https://filipelutz.github.io/DeepLink).
Click the **Open Music Player** button on the web page to trigger the DeepLink navigation into the app's music player screen.  

---

## Challenges Encountered  

### Setting Up DeepLink  
Setting up DeepLink was particularly challenging due to:  
- Creating the `.well-known` directory.  
- Ensuring the `assetlinks.json` file was correctly hosted and formatted on GitHub Pages.  
- Debugging and testing the correct URL fingerprint matching to ensure proper navigation.  

Despite these challenges, I successfully configured the DeepLink functionality by thoroughly testing and configuring all necessary parameters.  

---

## Conclusion  

In conclusion, the project successfully meets all the objectives by implementing DeepLink navigation and integrating media playback features. 
Using the DeepLink feature, users can directly access the Music Player screen via external web links, improving usability and accessibility. 
Additionally, the music player integrates three playback buttons—Play/Pause, Previous, and Next—to allow seamless track navigation and control.  

The development process involved configuring a DeepLink with GitHub Pages, debugging `assetlinks.json`, and setting up music playback using ExoPlayer. 
Though there were challenges in DeepLink implementation, they were resolved with research and testing, ultimately leading to a functional and user-friendly app.  

---

## References  

1. [The FULL Deeplinking Guide With Jetpack Compose - YouTube](https://www.youtube.com/watch?v=z6VlP0o_sDc)  
2. [Deep Linking in Jetpack Compose - YouTube](https://www.youtube.com/watch?v=UICbp-QAJ3g)  
3. [Deep Linking in Android - Official Documentation](https://developer.android.com/training/app-links/deep-linking?hl=en)  
4. [DeepLink Destination Navigation in Jetpack Compose](https://developer.android.com/guide/navigation/design/deep-link)  
5. [Media Playback with ExoPlayer - Media3 Guide](https://developer.android.com/media/implement/playback-app)  
6. [Supported Media Formats Documentation](https://developer.android.com/media/platform/supported-formats)  
7. [How to Build a Simple Video Player With Jetpack Compose & ExoPlayer - YouTube](https://www.youtube.com/watch?v=JX1fwti2LI4)  
8. [Kingdom South Africa - Source for Music Downloads](https://kingdomsouthafrica.co.za/)  

---

**Lecturer**: Eugene O'Regan  
**Module**: Mobile App Development 2  

---

## Disclaimer  

This project is for educational purposes only.  
The information and code presented in this repository demonstrate the application of DeepLink navigation and media playback concepts in Android development. 
They are not intended for commercial use. While efforts have been made to ensure the accuracy and reliability of the content, there may be errors or omissions. 
I am not responsible for any consequences arising from the use of this project or its implementation in real-world applications. 
Users are encouraged to verify and adapt the code as necessary for their specific use cases.

***Filipe Lutz***
