#!/bin/bash

apt-get -qq update && apt-get install -qqy --no-install-recommends openconnect maven wget
openconnect nomade.etu.univ-nantes.fr --juniper --user=berdjugin-jf -b < mdp.txt