#!/bin/bash
while true; do
  ab -n 50 -c 5 http://arch.homework/api/v1/user/2
  sleep 3
done

