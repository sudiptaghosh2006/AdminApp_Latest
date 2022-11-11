###############################################################
#             K8s Master (Network Interface)           #
###############################################################
resource "azurerm_network_security_group" "demo1" {
  name                = "acceptanceTestSecurityGroup1"
  location              = var.location
  resource_group_name   = var.resource_group

  security_rule {
    name                       = "test123"
    priority                   = 100
    direction                  = "Inbound"
    access                     = "Allow"
    protocol                   = "Tcp"
    source_port_range          = "*"
    destination_port_range     = "*"
    source_address_prefix      = "*"
    destination_address_prefix = "*"
  }
  security_rule {
    name                       = "test13"
    priority                   = 110
    direction                  = "Inbound"
    access                     = "Allow"
    protocol                   = "Icmp"
    source_port_range          = "*"
    destination_port_range     = "*"
    source_address_prefix      = "*"
    destination_address_prefix = "*"
  }
}
resource "azurerm_public_ip" "demo1" {
  name                = "mypublicip"
  #location            = azurerm_resource_group.example.location
  #resource_group_name = azurerm_resource_group.example.name
  location              = var.location
  resource_group_name   = var.resource_group
  allocation_method   = "Static"
  ip_version          = "IPv4"
}
resource "azurerm_network_interface" "K8s_master01_nic" {
  name                = "K8s-master01-nic"
  #location            = local.application_rg.location
  #resource_group_name = local.application_rg.name
  location              = var.location
  resource_group_name   = var.resource_group
  ip_configuration {
    name                          = "K8s-master01-ipc"
   #subnet_id                     = local.subnet_tier2.id
    subnet_id                     = var.subnet
    private_ip_address_allocation = "Dynamic"
    public_ip_address_id          = azurerm_public_ip.demo1.id
  }
}
resource "azurerm_network_interface_security_group_association" "demo1" {
  network_interface_id      = azurerm_network_interface.K8s_master01_nic.id
  network_security_group_id = azurerm_network_security_group.demo1.id
}
data "template_file" "K8s_master_01_script" {
 #template = "${file("../lib/master.sh")}"
  template = "${file("master.sh")}"
  vars = {
    public_key                = "${var.vm_osadmin_ssh_pubkey}"
    master_flag                  = "TRUE"
  }
}
###############################################################
#          K8s Master  (Virtual Machine Instance)       #
###############################################################

resource "azurerm_linux_virtual_machine" "K8s_master_01_vm_instance" {
  name                  = "K8s-master01-vm-instance"
 #location              = local.application_rg.location
 #resource_group_name   = local.application_rg.name
  location              = var.location
  resource_group_name   = var.resource_group
  network_interface_ids = ["${azurerm_network_interface.K8s_master01_nic.id}"]
 #source_image_id       = module.scb_linux7_image.image_id
  size                  = "Standard_E2ds_v4"
  admin_username        = var.vm_osadmin_user
  custom_data           = base64encode(data.template_file.K8s_master_01_script.rendered)
  #custom_data           = base64encode(file("master.sh"))

#  dynamic "admin_ssh_key" {
#    for_each = [for i in [var.vm_osadmin_ssh_pubkey] : i if length(i) > 0]
 #   content {
 #     username   = var.vm_osadmin_user
 #     public_key = var.vm_osadmin_ssh_pubkey
 #   }
 # }
os_disk {
    name = "K8s-master01-os-disk1"
    caching              = "ReadWrite"
    storage_account_type = "Standard_LRS"
  } 
source_image_reference {
    publisher = "OpenLogic"
    offer     = "CentOS"
    sku       = "7_9"
    version   = "latest"
}
#os_profile {
    #custom_data    = file("/home/kavya_chinta/terraform/master.sh")
#}
#  lifecycle {
#    ignore_changes = [custom_data]
# }
}
