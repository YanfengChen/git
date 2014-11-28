#!/bin/sh
# Configure this variable to point to the FieldTalk package:
FIELDTALK=../../lib/mbusmaster.jar
java -cp ${FIELDTALK}:. TcpSimple
