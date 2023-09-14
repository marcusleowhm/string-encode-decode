### Run the main method in the Main class to see result in console

#### 1. constant/ReferenceTable.java
- Contains static variable representing the given reference table

#### 2. service/StringService.java
- Contains the class for encode and decode method. 
- Also contains method for building strings.

#### 3. utility/NumberGenerator.java
- Contains methods for generating numbers. 
- It generates random number and a List of integers. 
- The random number is used for randomly selecting an offset at the start of encoding.
- The list of integer is used by StringService to calculate what are the indices to use for building encoded or decoded texts, after applying the offset generating by the random number.
