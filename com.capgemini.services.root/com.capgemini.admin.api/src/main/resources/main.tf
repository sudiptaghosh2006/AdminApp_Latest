provider "local" {
  version = "~> 1.4"
}

resource "local_file" "hello" {
  content = "Hello, Terraform"
  filename = "C:\\Users\\SGHOSH43\\Desktop\\TeraformDemo\\DemoFiles\\hello.txt"
} 

resource "local_file" "MyResouces" {

  content = "Hello, My Resources"
  
  filename = "C:\\Users\\SGHOSH43\\Desktop\\TeraformDemo\\DemoFiles\\MyResouces.txt"
}