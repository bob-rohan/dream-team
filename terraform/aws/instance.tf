resource "aws_instance" "dreamteam" {
  # ubuntu-minimal/images-testing/hvm-ssd/ubuntu-bionic-daily-amd64-minimal-20190910
  ami           = "ami-00152f16fe6a671c9"
  instance_type = "t2.micro"
  vpc_security_group_ids = ["${aws_security_group.dreamteam.id}"]
  subnet_id = "${aws_subnet.dreamteam.id}"
  private_ip = "10.0.0.4"
  key_name = "dreamteam"
  user_data = "${data.template_cloudinit_config.dreamteam-manager.rendered}"
}

data "template_file" "dreamteam" {
  template = "${file("${path.module}/init.cfg")}"

  vars = {
    dburi = "${var.dreamteam-dburi}"
  }
}

data "template_cloudinit_config" "dreamteam-manager" {
  gzip          = false
  base64_encode = false

  part {
    filename     = "init.cfg"
    content_type = "text/cloud-config"
    content      = "${data.template_file.dreamteam.rendered}"
  }
}
