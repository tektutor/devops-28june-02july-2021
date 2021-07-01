FROM ubuntu:16.04
MAINTAINER Jeganathan Swaminathan <jegan@tektutor.org>

RUN apt-get update && apt-get install -y openssh-server python3 sudo
RUN ln -s -f /usr/bin/python3 /usr/bin/python
RUN mkdir -p /var/run/sshd

RUN useradd -m -s /bin/bash tektutor

RUN echo 'root:root' | chpasswd
RUN echo 'tektutor:tektutor' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

RUN mkdir -p /root/.ssh
RUN mkdir -p /home/tektutor/.ssh
COPY authorized_keys /root/.ssh/authorized_keys
COPY authorized_keys /home/tektutor/.ssh/authorized_keys

COPY sudoers /etc/sudoers

EXPOSE 22
EXPOSE 80 
CMD ["/usr/sbin/sshd", "-D"]
