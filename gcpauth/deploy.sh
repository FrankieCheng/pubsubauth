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
