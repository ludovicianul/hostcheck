# hostcheck

hostcheck is a simple tool for checking hosts availablity and reachability.

# ci

[![Build Status](https://snap-ci.com/ludovicianul/hostcheck/branch/master/build_image)](https://snap-ci.com/ludovicianul/hostcheck/branch/master)

# build
    gradle jar
    
# usage
    java -Dmode=lookup -jar hostcheck.jar hostList.txt

This will parse the hostlist.txt file in lookup mode. It will take each row in the file and check if it can be resolved by the networked configured DNS server(s) or not.
The format of the supplied file is one host per line in the following format: host,PORT1,PORT2-PORTX. In **lookup** and **reach** modes, the ports are being ignored. In **connect** mode, for this particular example, it will try to connect to host:PORT1 and to all the ports in the PORT2-PORTX range.


# available modes

* **lookup**: will check if the supplied hosts are being solved by the networked configred DNS server(s)
* **reach**: check if the supplied hosts are reachable. It must run with root/admin priviledges in order simulate a ICMP ping
* **connect**: it will actually connect to the hosts and ports supplied in the file

# output
* **lookup**: for each host the output will look as follows: <supplied_host_row> [{<host_address> | <host_name> | <cannonical_host_name>} ... {<host_address> | <host_name> | <cannonical_host_name>}] - for each IP mapping (if more than one available)
* **reach**: for each host the output will look as follows: <supplied_host_row> <status> - where <status> is either 'is reachable' or 'is NOT reachable'
* **connect**: for each host the output will look as follows: <supplied_host_row>:<port>, <status> - where <status> is either 'able to connect!" or "NOT able to connect!"
