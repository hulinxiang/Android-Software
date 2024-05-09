# [G0 - Team Name] Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION textes in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative
- Firebase Repository Link: <insert-link-to-firebase-repository>
   - Confirm: I have already added comp21006442@gmail.com as a Developer to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
   - Username: comp2100@anu.edu.au	Password: comp2100
   - Username: comp6442@anu.edu.au	Password: comp6442

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID      |     Name      |      Role |
|:---------|:-------------:|----------:|
| u7633783 |  Linxiang Hu  |  Back-end |
| u7773637 |  Wenhui Shi   | Front-end |
| u7670526 | Yingxuan Tang |  Back-end |
| u7724192 |   Jin Yang    | Front-end |
| u7748799 |  Yichi Zhang  |  Back-end |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

1. **UID1, Name1**  I have 30% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Feature A1, A2, A3 - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - XYZ Design Pattern -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - ... (any other contribution in the code, including UI and data files) ... [Student class](../src/path/to/class/Student.java), ..., etc.*, [LanguageTranslator class](../src/path/to/class/LanguageTranslator.java): function1(), function2(), ... <br><br>

  - **Code and App Design** 
    - [What design patterns, data structures, did the involved member propose?]*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

  - **Others**: (only if significant and significantly different from an "average contribution") 
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

2. **UID2, Name2**  I have xx% contribution, as follows: <br>
  - ...



## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Green Book is a platform that revitalizes the concept of second-hand trading, aiming to encourage the recycling and reuse of items by connecting buyers and sellers. This initiative helps individuals easily find quality second-hand goods and offers sellers a space to rehome their pre-owned items. The platform is not just about buying and selling; it's a movement towards sustainable consumption and minimizing waste, in line with the United Nations' Sustainable Development Goal 12: Responsible Consumption and Production.*

*The user interface is crafted for ease, allowing sellers to set up listings complete with detailed descriptions, quality photos, and appropriate tags to enhance discoverability. For buyers, the platform provides tools to search for specific items, navigate through categories, and apply filters like location, price range, and item condition. Green Book also integrates a direct messaging system for seamless communication between buyers and sellers, facilitating inquiries and price negotiations.*

*By bridging the gap between those looking to find new homes for their cherished items and those seeking sustainable purchasing options, Green Book fosters a community committed to eco-friendly consumption practices.*

### Application Use Cases and or Examples

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here are some target user examples*

*Target Users: Individuals looking to sell their pre-owned items*

* *Sarah has decluttered her home and has several gently used furniture and decor items she wishes to sell. She signs up on Green Book, lists her items with all necessary details and photos, making them ready for new owners.*
* *Mark, looking for affordable furniture for his new apartment, stumbles upon Sarah's listings. He uses the platform's messaging feature to discuss the item's condition and negotiate the price.*
* *Once they agree on a deal, Mark purchases the item. Sarah is notified about the sale and coordinates with Mark to either pick up or deliver the furniture.*

*Target Users: Individuals looking to purchase second-hand items*

* *Emily is a college student on a tight budget who needs a new laptop for her studies. She browses Green Book's electronics category and finds several affordable options that meet her requirements.*
* *She contacts the sellers of the laptops she's interested in to ask questions about the devices' specifications, battery life, and overall condition.*
* *After comparing the options and reading reviews from previous buyers, Emily decides on a laptop and makes the purchase. The seller ships the laptop to Emily, who is thrilled to have found a high-quality device at a fraction of the cost of buying new.*

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

### Application UML

![ClassDiagramExample](media/_examples/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

<hr>

## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * *It is more efficient than Arraylist for insertion with a time complexity O(1)*
      * *We don't need to access the item by index for xxx feature because...*
      * For the (part), the data ... (characteristics) ...

2. *B+ Tree*
   * *Objective: used for storing user and post data locally for efficient searching, insertion, deletion, and update operations.*
   * *Code Locations:*
        * *BPlusTree class: defines the structure and operations of the B+ Tree, including insert, remove, query, and range query methods. It is implemented in the `BPlusTree.java` file.*
        * *BPlusTreeManagerUser class: manages the user data stored in the B+ Tree. It provides methods to get the tree instance and retrieve user data by user ID. Defined in the `BPlusTreeManagerUser.java` file.*
        * *BPlusTreeManagerPost class: manages the post data stored in the B+ Tree. It offers methods to get the tree instance, perform keyword search on posts, retrieve random posts for recommendation, and search posts by post ID. Implemented in the `BPlusTreeManagerPost.java` file.*
        * *The B+ Tree is used in the login and registration process (`LoginActivityBPlusTree.java` and `RegisterActivityBPlusTree.java`) to check user credentials and add new users.*
   * *Reasons:*
        * *B+ Tree provides efficient search, insertion, and deletion operations with a time complexity of O(log n), making it suitable for handling large amounts of data.*
        * *The tree structure allows for fast range queries, which can be useful for retrieving posts or users within a specific range of values.*
        * *B+ Tree keeps the data sorted, enabling efficient sequential access to the data, which is beneficial for scenarios like retrieving all posts or users.*
        * *The leaf nodes of the B+ Tree are linked, allowing for quick traversal of the entire dataset, which is useful for features like random post recommendation.*

3. *ArrayList*
    * *Objective: used for storing and manipulating collections of data, such as lists of posts, users, and search results.*
    * *Code Locations:*
        * *Used in various classes and methods throughout the project, such as `BPlusTreeManagerPost.randomRecommender()`, `BPlusTreeManagerPost.searchKeyword()`, and `BPlusTreeManagerUser.getUserViaUserId()`.*
    * *Reasons:*
        * *ArrayList provides dynamic resizing, allowing for efficient addition and removal of elements.*
        * *It offers fast random access to elements by index, which is useful when retrieving specific items from the list.*
        * *ArrayList is suitable for scenarios where the size of the collection is not known in advance or may change over time.*

4.  *HashSet*
    * *Objective: used for storing unique values and performing fast membership tests*
    * *Code Locations:*
        * *Used in the `BPlusTree` class to store unique data values associated with each key in the leaf nodes.*
    * *Reasons:*
        * *HashSet ensures that there are no duplicate values associated with a key in the B+ Tree.*
        * *It provides constant-time performance for basic operations like add, remove, and contains, making it efficient for checking the presence of a value.*
        * *ArrayList is suitable for scenarios where the size of the collection is not known in advance or may change over time.*

These data structures were chosen based on their performance characteristics and the specific requirements of the features they support. The B+ Tree serves as the main data structure for efficient storage and retrieval of user and post data, while ArrayList and HashSet are used for auxiliary tasks and specific scenarios within the project.
<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. *xxx Pattern*
   * *Objective: used for storing xxxx for xxx feature.*
   * *Code Locations: defined in [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and [class AnotherClass, lines l1-l2](url); processed using [dataStructureHandlerMethod](url) and ...
   * *Reasons:*
      * ...

<hr>

### Parser

### <u>Grammar(s)</u>
*[How do you design the grammar? What are the advantages of your designs?]*
*If there are several grammars, list them all under this section and what they relate to.*

Production Rules:

    <Non-Terminal> ::= <some output>
    <Non-Terminal> ::= <some output>


### <u>Tokenizers and Parsers</u>

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*

<hr>

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Users are able to log in to the application using their credentials. The application includes two pre-defined accounts for markers' access:
    - Username: comp2100@anu.edu.au, Password: comp2100
    - Username: comp6442@anu.edu.au, Password: comp6442 (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of feature: The login feature allows users to authenticate themselves and access the application's functionality. It verifies the provided username and password against the stored user credentials. Also provide register service and feel free to create your own account. <br>
   * Description of implementation: The LoginActivityBPlusTree handles the user interface for the login screen. It captures the user's input, validates the credentials against the predefined accounts or the user database, and grants access to the application upon successful authentication. The User class represents the user entity and encapsulates user-related information. <br>

2. [DataFiles]. The application utilizes a dataset consisting of more than 3,500 valid data instances. Basic data is stored in structured JSON formats. (easy)
   * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
   * Description of feature: The application relies on a dataset that contains meaningful information relevant to the app's functionality, including user and post. The data is stored in JSON files on firebase realtime database. The images of the posts are stored in Firebase storage. <br>
   * Description of implementation: The basic post data is from an open source dataset in the Internet and been processed to fit the application's requirements. The user data is generated using Python Faker library. The data files are stored in the GitLab repository and are also synced with Firebase Realtime Database for easy access and real-time updates. <br>
   * Link to the Firebase repo: [Firebase Repository](https://console.firebase.google.com/project/login-register-firebase-94766/overview) <br>

3. [LoadShowData]. The application loads and displays data instances from the dataset in an appropriate format based on the type of data. (easy)
    * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
    * Description of feature: The application retrieves data instances from the dataset in Firebase realtime database and presents them to the user in a structured and visually appealing format. The profile page show the user's information with the post owned by the user, and the item page shows the details of a specific item. <br>
    * Description of implementation: The FirebaseInit class is responsible for loading data from Firebase repository. The front-end Activities such as HomeActivity and ProfileActivity handles the presentation of the loaded data to the user. It determines the appropriate format for displaying each type of data, such as lists, tables, or charts, to enhance readability and user experience. <br>

4. [DataStream]. The application simulates user actions and interactions by creating data instances and feeding them to the app at regular intervals. (medium)
    * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
    * Description of feature: The application generates data instances that mimic user actions and interactions. These data instances are loaded at regular time intervals and visualized within the app when a user is logged in. For instance, a user can create a post and the post data will be uploaded to Firebase, then user can view it in the user interface. <br>
    * Description of implementation: The PostCreator class is responsible for generating and loading post data instances, including post title, description, images, and other relevant information. The ImageUploader class manages the creation and loading of images of posts. The data is stored in Firebase Realtime Database and Firebase Storage for persistence and retrieval. And then the data is displayed by the GlideImageLoader class in the app's UI for the user to interact with. <br>

5. [Search]. Users can search for specific information within the application using a query processor that understands user input based on predefined grammars. (medium)
    * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
    * Description of feature: The application provides a search functionality that allows users to retrieve information based on their queries. The query processor understands user input using predefined grammars and retrieves the relevant information from the dataset. <br>
    * Description of implementation: The _Parser Package_ using ANTLR handles the processing of user search queries. It utilizes a tokenizer to break down the user input into individual tokens and a parser to analyze the tokens based on a predefined formal grammar. The grammar defines the structure and syntax of valid search queries. The QueryProcessor interprets the user's query and retrieves the matching information from the dataset. The SearchActivity handles the user interface for the search functionality, allowing users to enter their queries and displaying the search results. <br>
   <br>

### Custom Features
Feature Category: Privacy <br>
1. [Privacy-Request]. Description of the feature  (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of your implementation: ... <br>
     <br>

2. [Privacy-Block]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [FB-Auth] Description of the feature (easy)
   * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
   * Description of your implementation: ... <br>

<hr>

### Surprise Features

- If implemented, explain how your solution addresses the task (any detail requirements will be released with the surprise feature specifications).
- State that "Suprised feature is not implemented" otherwise.

<br> <hr>

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
   - *A space bar (' ') in the sign in email will crash the application.*
   - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. xxx

...

<br> <hr>


## Team Management

### Meetings Records
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each commited within 2 days aftre the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](meeting-template.md)*
- ...
- ...
- [Team Meeting 4](link_to_md_file.md)
- ... (Add any descriptions if needed) ...

<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

This shall include an agreed procedure for situations including (but not limited to):
- e.g., if a member fails to meet the initial plan and/or deadlines
- e.g., if your group has issues, how will your group reach consensus or solve the problem?
- e.g., if a member gets sick, what is the solution? Alternatively, what is your plan to mitigate the impact of unforeseen incidents for this 6-to-8-week project? 
