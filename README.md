# pubsubauth
This project demonstrates how to use pubsub on an Android client. It mainly consists of two modules. android-app will be a runnable Android project, and gcpauth can be used to issue tokens. The token can be used on the Android client to send information to the gcp Pub/Sub service.

# Usage
Following [gcpauth deployment guide](./gcpauth/README.MD) to publish CloudRun service.
Following [Android App guide](./android-app/app/README.MD) to develop an android app to publish message to Pub/Sub service.
