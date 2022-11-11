# The id of subscription of deployment
#variable "deployment_subscription_id" {}

# Variable declaration for Region
variable "region" {
  description = "The region of deployment"
  default = "East US"
}

# Variable declaration for app_name
/*variable "app_name" {
  description = "The app_name name to be provided"
  default = "haas"
}*/

variable "local_app_name" {
description = "The local app_name name for the resource naming"
default = "adls"  
}

variable "environment" {
  description = "Environment classification: development|testing|staging|production"
  default = "development"
}
variable "subnet" {
  description = "subnet id for RG"
  default = "/subscriptions/262a3c1d-7fe3-4bfb-9f90-7a5b89109336/resourceGroups/tf-testing/providers/Microsoft.Network/virtualNetworks/tf-network/subnets/tf-subnet"
}

variable "resource_group" {
  default = "tf-testing"
}

variable "location" {
  default = "East US"
}

/*variable "owner_users" {
  type    = list(string)
  default = []
}

variable "owner_groups" {
  type    = list(string)
  default = []
}*/

variable "tags" {
  type    = map(string)
  default = {}
}

variable "trigger_by" {
  default = "kavya"
}

/*variable "dr_rg_name" {
  description = "The resource group name"
  default = "DataRobotResourceGroup"
}*/

variable "vm_size" {
  description = "Size of VM"
  default     = "Standard_F2s_v2"
}

variable "vm_disk_type" {
  description = "Type of Managed disk"
  default     = "StandardSSD_LRS"
}

variable "vm_osadmin_user" {
  description = "username of vm os admin"
  default     = "scbuser"
}

variable "vm_osadmin_ssh_pubkey" {
  description = "ssh key"
  default = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDadoYF07zIoyOquBq0nhaf4QEcyTueBYcYb7LaG1bt2XnVbvZKdnTDQNfmAvZFmo+ixYns8W3gseF/W/NSAhH1H2bze33rUhGPoeLzOjdd4hFLR4fQMN6FRJ5SxYuCZcWTx9bN4TQEHm6zJ0/dwU9x4eJu6XgzmbRBI/MYWwvjHjNFY1DlGJ6jR+bX5r/XW3a0PucJtpk5hdXPSLIWjlXGbZam9lrGymW/+dgIOppMeGSHAfEkRYwbDAGADy3vRDSNiKytFljG2OgmUrR4ThYLuRZ1staYVefX8M2fMUiymRmNLD0+gROmJ+lVJKGYuP49pJ+Tey1l8uAIZALMYEhFYUbtBgjxDOPIIEj/OOT7PWNSZLe2FEBPldfbFkEcUgOT4T2obCCJXbmgAW6RBj+qMAEDxMDj534mnEBnvlNP3f/C2ZjhfCthUqmfoCoNHhxl8AsNX7YkG7EoHqZUAHXupgFMsKZNMmNpzYbLH6N6hYFpCjCGUli6LikCGUzhUUU= generated-by-azure"
}

/*variable "tag_map" {
  description = "Tags for resource"
  type        = map(string)
  default     = {}
}*/

variable "vm_count" {
  description = "Number of VM request"
  default     = 1
}

variable "vm_custom_data" {
  description = "VM Custom Data, process by cloud-init"
  default     = null
}

variable "disk_encryption_set_id" {
  description = "Managed disk encryption set id"
  default     = null
}

variable "managed_identity_igds" {
  description = "A list of user managed identity ids to be assigned to the VM"
  type        = list(string)
  default     = []
}

variable "disk_size_gb" {
  description = "The Size of the Internal OS Disk in GB, if you wish to vary from the 32GB size used in the image."
  default = null
}

variable "data_disk_size_gb" {
  description = ""
  default = [50, 60]
}


variable "datalake_enabled" {
  description = "Enable for Data Lake File System"
  default     = true
}

