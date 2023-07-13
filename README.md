# Video Downloader in Kotlin

A simple Kotlin application that downloads videos from an array of URLs and saves them to the device's shared storage.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Android Studio
- Kotlin Plugin for Android Studio
- Internet access for downloading dependencies

### Installing

- Clone the repository to your local machine.
- Open the project in Android Studio.
- Sync the project with Gradle files.
- Run the project on an emulator or an actual Android device.

## Usage

The primary function of this application is to download videos from an array of URLs. You can call the `downloadVideos()` function from an Activity or other Context:

```kotlin
downloadVideos(this, arrayOf("http://example.com/video1.mp4", "http://example.com/video2.mp4"))
```

## Built With

- [Kotlin](https://kotlinlang.org/) - The programming language used
- [OkHttp](https://square.github.io/okhttp/) - HTTP client for downloading videos

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

- [Square](https://github.com/square) for the OkHttp library
