# arduino-nano-setup
Arduino Nano V3.0 ATmega328 CH340G playground on Ubuntu 15.10

![Arduino Nano V3.0 ATmega328 CH340G](https://github.com/Pozo/arduino-nano-setup/blob/master/arduino-ano-v3.0-atmega328-ch340g.jpg "Arduino Nano V3.0 ATmega328 CH340G")

#### Driver (already installed)

    lsmod | grep 'ch'
    modinfo ch341
    http://lxr.free-electrons.com/source/drivers/usb/serial/ch341.c

#### Arduino IDE

Arduino IDE [here](http://arduino.cc/en/Main/Software).

    sudo ./arduino

Arduino IDE settings : Tools/Board  `Arduino Nano` Tools/Processor `ATmega328` Tools/Programmer `Arduino as ISP`
Arduino [Language Reference](https://www.arduino.cc/en/Reference/HomePage)

#### Debug with Wireshark

    modprobe usbmon
    sudo wireshark

#### Control with Java

Official article, [Arduino and Java](http://playground.arduino.cc/Interfacing/Java)

    sudo apt-get install librxtx-java
    sudo usermod -a -G dialout $USER
    sudo adduser $USER dialout
    sudo reboot

##### Run sample app #1

    mvn exec:java
    mvn exec:java -Dport.id=/dev/ttyUSB0

##### Run sample app #2

    mvn clean install
    java -jar target/arduino-0.0.1-SNAPSHOT.jar
    java -jar -Dport.id=/dev/ttyUSB0 target/arduino-0.0.1-SNAPSHOT.jar

#### DTR causing a reset

##### Description

    http://stackoverflow.com/a/35406863
    http://stackoverflow.com/questions/3918032/bash-serial-i-o-and-arduino

##### Terminal and Arduino IDE serial monitor dumps

`echo "ON" > /dev/ttyUSB0`		      - [ubuntu.terminal.echo.pcapng](https://github.com/Pozo/arduino-nano-setup/blob/master/ubuntu.terminal.echo.pcapng "ubuntu.terminal.echo.pcapng")


`using Arduino IDE serial monitor`	- [arduino.ide.serial.monitor.pcapng](https://github.com/Pozo/arduino-nano-setup/blob/master/arduino.ide.serial.monitor.pcapng "arduino.ide.serial.monitor.pcapng")

    stty -a < /dev/ttyUSB0
    echo "ON" > /dev/ttyUSB0	wont work
    echo "OFF" > /dev/ttyUSB0	wont work

