resource "aws_vpc" "dreamteam" {
  cidr_block = "10.0.0.0/28"
  enable_dns_hostnames = true
  enable_dns_support = true
}

resource "aws_eip" "dreamteam" {
  instance = "${aws_instance.dreamteam.id}"
  vpc      = true
}

resource "aws_internet_gateway" "dreamteam" {
  vpc_id = "${aws_vpc.dreamteam.id}"
}

resource "aws_route_table" "dreamteam" {
  vpc_id = "${aws_vpc.dreamteam.id}"
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.dreamteam.id}"
  }
}
resource "aws_route_table_association" "dreamteam" {
  subnet_id      = "${aws_subnet.dreamteam.id}"
  route_table_id = "${aws_route_table.dreamteam.id}"
}

resource "aws_subnet" "dreamteam" {
  cidr_block = "10.0.0.0/28"
  vpc_id = "${aws_vpc.dreamteam.id}"
}

resource "aws_security_group" "dreamteam" {
  name        = "dreamteam"
 
  vpc_id = "${aws_vpc.dreamteam.id}"
  
  ingress {
    cidr_blocks = [
      "0.0.0.0/0"
    ]
  
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
  }
  
  ingress {
    cidr_blocks = [
      "0.0.0.0/0"
    ]
  
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
  }
  
  ingress {
    cidr_blocks = [
      "0.0.0.0/0"
    ]
  
    from_port   = 8088
    to_port     = 8088
    protocol    = "tcp"
  }
  
  // Terraform removes the default rule
  egress {
   from_port = 0
   to_port = 0
   protocol = "-1"
   cidr_blocks = ["0.0.0.0/0"]
  }
}