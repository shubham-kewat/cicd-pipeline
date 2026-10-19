
output "api_endpoint" {
  description = "Base URL of the HTTP API"
  value       = aws_apigatewayv2_api.app.api_endpoint
}


output "artifact_bucket_name" {
  description = "S3 bucket for versioned deployment artifacts"
  value       = aws_s3_bucket.artifacts.bucket
}
