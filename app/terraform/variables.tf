
variable "project_name" {
  description = "Name of the CI/CD project"
  type        = string
  default     = "lambda-cicd-platform"
}

variable "lambda_function_name" {
  description = "Name of the AWS Lambda function"
  type        = string
  default     = "lambda-cicd-platform"
}

variable "aws_region" {
  description = "AWS region for deployment"
  type        = string
  default     = "ap-south-1"
}
