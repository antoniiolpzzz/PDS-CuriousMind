# 📋 Project Testing

This section describes the strategy and current status of the tests implemented in the project.

## 🧩 Test Structure

### ✅ Unit Tests (Model - MVC)

- Unit tests have been implemented for each model class.
- For every model class, there is a corresponding test class.
- Each public method has been tested to verify correct functionality.
- These test classes are included in the directory [src/test](link to directory)

### 📊 Coverage Statistics (Maven + Coverage Plugin)

- The Maven plugin with JaCoCo was used to generate code coverage reports.
- Types of coverage:
  - **Line coverage**
  - **Branch coverage**
- The generated report is shown below:  
📸 ![image](https://github.com/antoniiolpzzz/PDS-CuriousMind/raw/b89e72fa54b8db1e46a37aecefd982192e917665/Documentation/Test/informeCobertura.png)

### 🔄 Integration Tests

- Test classes are being implemented to validate the combined behavior of multiple components.
- Objective: ensure that system modules interact correctly.
- These tests are also included in the directory [src/test](link to directory)

### 🎯 Acceptance Tests

- Validation of complete functionalities from the user's perspective.
- Demonstration videos have been created to show how users interact with the application.
- [Demo Video](https://dai.ly/k3ALFKPBzbRbZODhqhq)  
[![Demo Video](https://github.com/antoniiolpzzz/PDS-CuriousMind/blob/19fbc344a0d8a998837dee42300f22f802e38d75/Documentation/Test/Captura%20-%20Video%20demostrativo.png)](https://geo.dailymotion.com/player.html?video=k3ALFKPBzbRbZODhqhq)

### 🛠️ Tests with Mockito

- Mockito has been used to:
  - Simulate dependent components.
  - Isolate the code under test.
  - Facilitate the writing of unit and integration tests.

---
