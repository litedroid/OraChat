# OraChat
An Android app based on the requirements found at: http://docs.oracodechallenge.apiary.io

- MVP architecture
- Activities with Fragments including navigation
- REST API calls
- Gson to deserialize server responses

Minimum Android level - 19 - v4.4

### Utilizing:
- Retrofit for asynchronous API calls
- Gson
- Android Annotations
- Joda time
- Constraint Layout

### Implemented:
- Login
- Register
- Logout
- Chat List
- Chat Message List

### Not Implemented:
- Add Chat Message
- Add Chat
- Edit Chat

### To Do:
- Handle error messages returned from server
- Login/Register client validations
- Paging for chat and message lists
- Progress views for async calls
- Test MVP layers
- Save email / name to local prefs
- Network connection check/listener
- Add loader/manager/repository for chats and messages