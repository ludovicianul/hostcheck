# hostcheck

hostcheck is a simple for checking hosts availablity and reachability.

# ci

[![Build Status](https://snap-ci.com/ludovicianul/hostcheck/branch/master/build_image)](https://snap-ci.com/ludovicianul/hostcheck/branch/master)

# build
    gradle jar
    
# usage
    java -Dmode=lookup -jar hostcheck.jar hostList.txt

This will parse the hostlist.txt file in lookup mode. It will take each row in the file and check if it can be resolved by the networked configured DNS server(s) or not.


# available modes

