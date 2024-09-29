# Introduction
Create a project to generate GCP token

# Clone the git

# Create a service account for Cloud Run to generate the access token.
Service Account Create Guide: https://cloud.google.com/iam/docs/service-accounts-create
Service Account attached Roles must have:
Service Account Token Creator
Service Account User

# Create a service account impersonated to invoke pubsub
Service Account attached Roles must have:
Pub/Sub Publisher

# Change the conf/gcpauth.conf file
Update secret_key: change to your own key, and the md5 of this key will used for Android to invoke the app.
impersonated_service_account: created above.

# Deply app
change the project id, and the service account url created above.
```bash
gcloud run deploy \
  --project {REPLACE YOUR PROJECT ID} \
  --source=. \
  --cpu=1 \
  --memory=3096M \
  --port=8080 \
  --region=us-central1 \
  --max-instances=1 \
  --min-instances=1 \
  --service-account={REPLACE YOUR SERVICE ACCOUNT}
```