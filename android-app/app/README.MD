#  Introduction
This is the Android App test code. You can use this Android project to access pubsub.

# Clone the repo
Clone the repo from Git.

# Change the configuration.
Change the class ConfigConstant.java, update the Topic/GCP_AUTH_URL/GCP_AUTH_TOKEN/PROJECT_ID. 
TOPIC: you can create a topic following the guide, https://cloud.google.com/pubsub/docs/create-topic
GCP_AUTH URL: https://{AUTH URL}/v1/token/
GCP_AUTH_TOKEN: Token is set to the MD5 value of secret_key in the Java project.
PROJECT_ID: Use your own project id.
```java
    //Change your topic.
    String TOPIC = "{REPLACE YOUR TOPIC}";

    //Change your own AUTH server URL
    String GCP_AUTH_URL = "{REPLACE YOUR AUTH URL}";

    //Change your own Auth TOKEN
    String GCP_AUTH_TOKEN = "{REPLACE YOUR AUTH TOKEN}";

    //change your own project id
    String PROJECT_ID = "{REPLACE YOUR PROJECT ID}";
```