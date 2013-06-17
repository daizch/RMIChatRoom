#!/bin/sh

cd ../src
javac -d ../bin/ *.java
cd ../bin
rmic chatRoom.service.ChatRoomServiceImplement
rmic chatRoom.service.ServiceFactoryImplement
cd ../bat
