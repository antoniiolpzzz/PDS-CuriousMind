<div align="center">

# 🧠 CuriousMind

[![License](https://img.shields.io/badge/LICENSE-MIT-violet.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/STATUS-Always_Evolving-green.svg)](#)
[![Version](https://img.shields.io/badge/VERSION-1.0.0-orange.svg)](#)

_An innovative learning platform for curious minds_

<div class="nav-menu" style="display: flex; flex-wrap: wrap; justify-content: center; gap: 8px; margin: 1.5em auto; max-width: 600px;">
   <a href="#-about" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; 
   background: linear-gradient(135deg,rgb(160, 193, 98),rgb(136, 149, 18)); border-radius: 20px; box-shadow: 0 2px 4px rgba(74, 144, 226, 0.3); transition: transform 0.2s, box-shadow 0.2s;">📖 About</a>
  <a href="#-features" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #4a90e2, #357abd); border-radius: 20px; box-shadow: 0 2px 4px rgba(74, 144, 226, 0.3); transition: transform 0.2s, box-shadow 0.2s;">✨ Features</a>
  <a href="#-getting-started" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #7ac29a, #5daf82); border-radius: 20px; box-shadow: 0 2px 4px rgba(122, 194, 154, 0.3); transition: transform 0.2s, box-shadow 0.2s;">🚀 Getting Started</a><a href="#-user-manual" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #4a90e2, #357abd); border-radius: 20px; box-shadow: 0 2px 4px rgba(74, 144, 226, 0.3); transition: transform 0.2s, box-shadow 0.2s;">👤 User Manual</a><a href="#-use-cases" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #4a90e2, #357abd); border-radius: 20px; box-shadow: 0 2px 4px rgba(74, 144, 226, 0.3); transition: transform 0.2s, box-shadow 0.2s;"> 📲Use Cases</a>
  <a href="#-contributing" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #9b6ddf, #7b4fc9); border-radius: 20px; box-shadow: 0 2px 4px rgba(155, 109, 223, 0.3); transition: transform 0.2s, box-shadow 0.2s;">🤝 Contributing</a>
  <a href="#-license" style="flex: 0 1 auto; padding: 8px 16px; text-decoration: none; color: white; font-weight: bold; background: linear-gradient(135deg, #ff7676, #ff5252); border-radius: 20px; box-shadow: 0 2px 4px rgba(255, 118, 118, 0.3); transition: transform 0.2s, box-shadow 0.2s;">📄 License</a>
</div>

</div>

---

## 👥 Team Information

- **Professor:** _Jesús Sánchez Cuadrado_
- **Team Members:**
  - [_Mercedes López Caballero_](https://github.com/mrcdslpz)
  - [_Antonio López Toboso_](https://github.com/antoniiolpzzz)
  - [_Javier Guardiola González_](https://github.com/JavierGuardiolaG)

## 📖 About

CuriousMind is an innovative learning platform inspired by successful applications like _Duolingo_ and _Mochi_. Our platform is designed to facilitate learning and reviewing concepts across various domains through an interactive card-based system.

You can easily understand its main structure by looking at our **domain model**. Click [here](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Design/DomainModel/CuriousMind%20-%20DomainModel.png) to see it!

### 🎯 Key Features

- Create and share custom learning courses
- Learn through different types of interactive exercises
- Track progress with detailed statistics
- Use various learning strategies (sequential, spaced repetition, random)
- Save and resume learning sessions

Whether you're learning a new language, preparing for a quiz show, studying programming concepts, or exploring music theory, CuriousMind provides a flexible and engaging learning experience tailored to your needs.

## ✨ Features

You can watch a demostrative video related to all the features of our app. You will find it in this [readme🇪🇸](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Documentation/Test/readmeES.md) | [readme🇬🇧](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Documentation/Test/readme.md) related to the tests made!

### 🎴 Interactive Learning Cards

- Create customized flashcards with text and images
- Support for multiple question types:
  - **Multiple Choice** - Test your knowledge with options
  - **Fill-the-gap** - Practice recall
  - **FlashCards** - Play with visual answers
  - **Form the sentence** - Create your knowledge 
  - **And more coming soon!**
- Rich text formatting for better content presentation

### 🧮 Smart Learning System

- Thanks to "Shuffled mode", you will actually learn, not exercise your memory!
- Spaced repetition system for optimal retention
- Performance analytics and learning insights


### 📚 Course Management

- Create and organize courses by topics and difficulty levels
- Import course content from your computer
- Share courses with other users
- Fun "5-lives" system to learn challenging yourselves


### 🎨 User Experience

- Clean and intuitive interface
- Progress tracking and achievements
- Mobile-responsive design
- Offline learning capability

All of this can be seen on our [Mockups🇪🇸](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Requisitos/MockUpES.md) |  [Mockups🇬🇧](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Requisitos/MockUpEN.md).

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) **21** or higher
- Maven for dependency management
- Your favorite IDE (IntelliJ IDEA recommended)

### ⚙️ Installation

Disclaimer: There are already compiled artifacts in Java/target/artifacts directory matching the main branch version.

1. Clone the repository
   ```bash
   git clone https://github.com/antoniiolpzzz/PDS-CuriousMind.git
   ```
2. Navigate to the project directory
   ```bash
   cd PDS-CuriousMind/Java
   ```
3. Build the project
   ```bash
   mvn clean install
   ```
## 👤 User Manual

Click [_here🇪🇸_](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Documentation/Manual/ManualUsuario.md) to go to our Spanish detailed User Manual, and [_here🇬🇧_](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Documentation/Manual/UserManual.md) to go to the English version.



## 📲 Use Cases

Click [_here🇪🇸_](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Requisitos/readmeES.md) to go to our detailed use cases list in Spanish, and [here🇬🇧](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/main/Requisitos/readme.md) to go to the English version, including:


1. [CDU 001](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/51): Log in
2. [CDU 002](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/53): Sign up
3. [CDU 003](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/2): Create course
4. [CDU 004](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/3): Select course
5. [CDU 005](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/5): Register course
6. [CDU 006](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/4): Make course
7. [CDU 007](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/6): Follow progress
8. [CDU 008](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/12): Repeat failed (Additional feature)
9. [CDU 009](https://github.com/antoniiolpzzz/PDS-CuriousMind/issues/13): Increase level (Additional feature)




## 🤝 Contributing

We welcome contributions to CuriousMind! Here's how you can help:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Inspired by Duolingo and Mochi
- Thanks to all contributors who participate in this project
- Special thanks to _Jesús Sánchez Cuadrado_ for guidance and support

---

<div align="center">
Made with ❤️ by the CuriousMind Team
</div>
