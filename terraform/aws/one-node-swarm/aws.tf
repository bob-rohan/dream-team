provider "aws" {
  region      = "eu-west-2"
  access_key = "${var.accesskey}"
  secret_key = "${var.secretkey}"
}

resource "aws_key_pair" "dreamteam" {
  key_name   = "dreamteam"
  public_key = "${var.dreamteam-pubkey}"
}

output "dreamteam_ip" {
  value = "${aws_eip.dreamteam.public_ip}"
}