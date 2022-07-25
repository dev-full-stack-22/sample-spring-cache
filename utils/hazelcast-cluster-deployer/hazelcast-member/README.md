### Update the .env and set the properties
Set the HZ_NETWORK_PUBLICADDRESS, HZ_CLUSTERNAME, MANAGEMENT_CENTER_PORT


### Create the directories:
 -  create data directory for the management node
   mkdir -p data
   sudo chown -R 10001:65533 data


### To start both of them, execute the following command.

    docker-compose up -d   

### Access the Management portal on port 8999 and create the local admin account and add the cluster



# Note: For running only hazelcast member comment the management-center in docker-compose file.

 