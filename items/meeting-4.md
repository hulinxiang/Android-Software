# G58

## Team Meeting 3 - Week 9 - 2024-05-03 (4pm-5pm)
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

3. **Other Matters**:
    - Confirm the next meeting will be held on May 7th.


## Agenda Items
| Number  |                                                                                                                                                                                                                 Item |
|:--------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| 0000001 |                                                                                                                 Home page recommended content: fixed display 4 posts. Hit Refresh and get four more. All random show |
| 0000002 |                                                                                                                                                                  Users can add tag search to narrow down the display |
| 0000003 |                                                                                                                                Modify the user and post classes (to facilitate the establishment of user_post table) |
| 0000004 |                                                                                         Communicate information that the user must fill in for the Create post function. From the attributes of the user, post class |

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
| Task                                                                                                                                                 |  Assigned To  |  Due Date  |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------|:-------------:|:----------:|
| In the Post class, the link attribute is changed to save the image encoding, and the image is loaded. Add constructor (may involve data on firebase) |  Yichi Zhang  | 2024-05-07 |
| Change the Firebase user data to the encrypted password, add a 6442 administrator test account data, and the user data should be generated again     |  Yichi Zhang  | 2024-05-07 |
| firebase upload update data function                                                                                                                 | Yingxuan Tang | 2024-05-07 |
| User class: adding admin attributes (change them quickly)                                                                                            | Yingxuan Tang | 2024-05-07 |
| Searching function for user to filter all the posts                                                                                                  |  Linxiang Hu  | 2024-05-07 |


## Scribe Rotation
The following dictates who will scribe in this and the next meeting.
| Linxiang Hu |
| :---: |
| Wnhui Shi |
| Yingxuan Tang |
| Jin Yang |
| Yichi Zhang |