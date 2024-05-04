# G58

## Team Meeting 3 - Week 9 - 2024-05-01 (3pm-4pm)
**Absent: N/A**
<br>
**Lead/scribe: Yingxuan Tang**

## Agreed Procedure
Stand up Procedure: 
1. **Opening**:
    - Recorder gives a brief overview of the meeting.
    - Attendance confirmation.

2. **Progress Report**:
    - We each reported what we had accomplished and summarized what had been accomplished.
    - The completed content is:
      BPlusTree
      Realize the local storage of user information
      Login registration function

3. **Other Matters**:
    - Confirm the next meeting will be held on May 3th.

4. **Conclusion**:
    - The front end and back end have a communication and are clear about their respective progress
    - Schedule the next functions that need to be completed
    - Front-end functionality assurance, connecting front and back-end.


## Agenda Items
| Number  |                                                                 Item |
|:--------|---------------------------------------------------------------------:|
| 0000001 |                               API unification for better integration |
| 0000002 | Ensuring successful compilation and execution before pushing changes |
| 0000003 |     Front-end functionality assurance, connecting front and back-end |


## Meeting Minutes
- Project Theme: Second-hand trading platform
- Our second-hand trading platform contributes to UN Sustainable Development Goal 12 by promoting sustainable consumption and production patterns. By facilitating the buying and selling of pre-owned items, we reduce resource consumption, minimize waste generation, and encourage sustainable consumer behavior.
- Work allocations:
    - Front-end: Jin Yang
    - Front-end and back-end integration: Wenhui Shi
    - Back-end and database integration: Yichi Zhang
    - Backend: Linxiang Hu, Yingxuan Tang
- functionalities
    -  User Management
        1. Register new users
        2. User login and authentication
        3. User profile management (including avatar, nickname, contact information, etc.)
        4. User account management (password reset, account cancellation, etc.)
    - Item Management
        1. Publish item information (including item name, description, price, images, etc.)
        2. Edit and delete published item information
        3. Item categorization and search functionality
        4. Item detail page display
    - User reviews and ratings
        1. Can rate users and create an honest and transparent trading platform
    - Social Features
        1. User post dynamics and comments
        2. Sharing functionality
    - Security and Privacy
        1. Data encryption and secure transmission
        2. User privacy protection (personal information protection, privacy settings, etc.)
        3. Payment security and transaction protection
    - Backend Administration
        1. Admin login and permission management
        2. Item review and management
        3. User management and behavior monitoring
        4. Data statistics and report generation
- Technical Architecture
    - Frontend: Android front-end
    - Backend: Java
    - Database: Firebase
    - Server and hosting: Firebase


## Action Items
| Task                                                                                                                                                                                                                                              |  Assigned To  |  Due Date  |
|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|:----------:|
| Modify the BPlusTree to implement the functionality of different tables                                                                                                                                                                           |  Linxiang Hu  | 2024-05-05 |
| Retrieve user information from firebase and store it in a local BplusTree data structure                                                                                                                                                          | Yingxuan Tang | 2024-05-03 |
| The User and Post classes are defined to generate fake data to be passed to firebase                                                                                                                                                              |  Yichi Zhang  | 2024-05-03 |



## Scribe Rotation
The following dictates who will scribe in this and the next meeting.
| Yingxuan Tang |
| :---: |
| Linxiang Hu |
| Jin Yang |
| Yichi Zhang |
| Wenhui Shi |