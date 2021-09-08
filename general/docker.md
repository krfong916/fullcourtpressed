- CDN for caching the SPA (thick UI client that manages state)
- Server for hosting the Node.js code
- Server for hosting postgres instance

AWS

- account to handle automated deployments

  - user account to change server configurations and spin up new servers

- what is elastic beanstalk
- ec2
- IAM user and API keys
- S3 buckets
- Route 53
- queue service
- cloundfront and cloud watch

- Beanstalk architecture?
  - loadbalancer
  - provisioning server resources
  - application environments?
  - IP resolution

## Registry Service

- need to use a registry service for my containers
- provides an CLI API for anyone that needs to pull an image (server, db, client)

## Docker

- Build run and share container applications
- Create an image of the filesystem (a blueprint) with a config (dockerfile) and create containers (instances) of that image
- the container is a virtualized process that has access to the system's resources
- How does a container normalize port numbers? All traffic is forwarded to a designated docker port, even if an application may be specify its own port internally. In a microservice architecture, it can get confusing about what port a particular application runs on. Docker removes the game of port-guessing.
- Docker compose is for networking
- Images are executable packages of everything needed to run the code. Containers are runtime instances of an image (what the image becomes in memory when executed)
- All containers share the same kernel

### Dockerfile

- a dockerfile is built on shifting layers. Code at the very top should not change. Code at the bottom should
- The base image tells the container what to install - programming language, runtime
- commands to run the application and environment
- port number to serve out of

How to write efficient dockerfiles - build time, image size, maintainable, security

## Route 53

- provides domain name to IP resolution
- we specify an `NS` record to resolve to an `A` record set i.e. a domain nameserver record set to an aws server instance (or host IP address)

## S3

- key, value store for object (file) storage
- do we host our client application in s3? and have route 53 direct to it?
  - the idea would be: store client app in s3, route 53 handles domain name resolution (maps domain name to s3 bucket). Cloudfront is the CDN that also stores our client app at edge. If it's a cache hit, return the app, if a cache miss, then go to aws which resolves to s3
  - client app requests are made to an ec2 server

## Cloudfront

- Cloudfront is a CDN
- Route 53 handles the domain name to IP resolution. Route 53 will forward the request to cloudfront first, checking the requested content is in cache. If not then the CDN forwards the request, pointing to an S3 bucket that hosts the static website. Cloudfront caches assets in S3 -> this reduces the number of requests to our server
- The problem now is cache invalidation - when we want to deploy a change to our client application, how can we invalidate the cache?

## Elastic Beanstalk

- it's an orchestration service
- handles capacity provisioning, load balancing, auto-scaling, application health monitoring
- forget it though - I'm going to roll out my own docker, nginx, postgres solution
- each service will be in a container and I will handle networking between the two
- datadog will be used for performance monitoring - of my API service

- docker compose for networking?
- what's the minimum amount of things I need to build my API service?

- how to test docker container locally?

  - use goss

- we run docker build the image, is this true?
- we run docker run to create a container, is this true?

## Docker Questions

- `FROM`: official image that contains the interpreter
- `WORKDIR`: creates a working directory folder
- `COPY`: copies local files into the container
- `EXPOSE`: indicates the ports the container listens for connections
- `ENV`: an intermediate layer. It allows us to declare constant
- `VOLUME`: exposes any database storage area, config storage, or files/folders created by the docker container. We are encouraged to use `VOLUME` for mutable/user-serviceable parts of the image
  variables in a program
- the difference between `RUN` and `CMD`
  - `RUN`: used for installing packages. Each run statement is cached. If we write a new RUN statement, the RUN statements that are unchanged from the previous image build won't be executed, instead, the cached version will be used. We can potentially get an outdated version of our packages. (RUN commands are intermediate layers)
  - We can specify `-y` command to ensure our Dockerfile installs the latest package versions. This is known as cache busting
  - `CMD`: used to run the software contained in my image along with any arguments
- what order should I place each command for caching?
  - Order from least to most likely to change to reap caching benefits
- what is this command: `docker container run --publish 8000:8080 --name test init-test:1.0`
  - `docker container run`: run a command in a new container
  - `--publish 8000:8080`: corresponds to `$HOSTPORT:$CONTAINERPORT`. In other words, publish a container's port to the host port
  - `--name test`: assign a name to the container based on the name of the image
  - `image-name`: define the image that we want to create a container from
- what's the use of `EXPOSE` if we declare a `PORT` env variable?
- In the docker networking environment, what component does each command modify, `EXPOSE`, `--publish` and `ARG PORT, ENV PORT`?

  - Here's the deal: `EXPOSE`: tells Docker that our container's service can be connected to on a given port. `EXPOSE` allows containers to be accessed _within_ the same network. Meaning, `EXPOSE` is good for inter-container communication. It is an explicit statement that documents on which port our service listens on
  - If `EXPOSE` is defined, we can publisha port back to our Docker host with `-p` so the outside world can connect to our service
  - `--publish $HOSTPORT:$CONTAINERPORT` allows the service in the container to be accessible from anywhere, even outside of Docker.
    - Note: for our client-facing service, we must specify a port to listen on in the application itself. This port must be the same as the `--publish` port
  - We cannot publish ports in a Dockerfile because that decision is strictly reserved for the local administrator of the docker container host

* What are the differences in networking capabilities between a dockerfile and docker-compose?
* we shouldn't leave the port to expose up to the user, the port should be an env variable - i.e. we need build-time variables and run-time environment variables. First, how can we do so using just a Dockerfile and then using docker-compose?
* How can we reduce the size of a Docker container? What's the difference between virtual and actual image size?

- how do the docker compose commands differ from the commands specified in dockerfile? do they overwrite?
  what is the context?
- how do we expose ports?

order matters for caching
use a specific copy to avoid a cache bust
identify cacheable units
reduce image size
remove unnecessary dependencies
remove package manager cache
use specific tags
Use multi-stage builds to remove build dependencies

https://we-are.bookmyshow.com/understanding-expose-in-dockerfile-266938b6a33d

how to manage compose files for different environments?
`docker-compose.yml` contains the base configuration. `docker-compose-override.yml` contains the overrides for existing services or entirely new services. If the same services exist in both files, then the rules are merged.
Use multiple `override` files using `-f`

how to reduce time for docker build
what is the role of tagging in docker for CI/CD?
if services are clustered, how do we prevent one change affecting the build of a whole stack

Microservices is an architecture for software systems where unctionality is separated into independently deployable and runnable components that communicate with each other over an IP based fabric with message queues HTTP or RPC.
It allows us to patch a single component without full system downtime and add multiple instances of components under heavy load without wasting resources by duplicating underused components. We could not achieve this with a single, mega container.

How can we use docker compose to achieve this?
Docker-compose can download/build images for each container
start containers in order
create a virtual network connection connecting all containers together. Each container is assigned a DNS entry matching the service name. This reduces component port collision because each container is a different host in the network i.e. server:3000, web:3000

How to manage containers as we change environments?
What needs to change?

- environment variables
  - we don't want to store the env variables inside the docker-compose files because the images contain a history, and therefore, the information can be compromised --> store in an env file
  - how to read from the correct env file? each docker-compose has an env variable and file path associated - dynamic
  - use the env_file option
- how information is stored

  - ex: dev needs to hotreload t/f containers filesystem must be linked to local filesystem. prod image should be immutable, no volume

  take source inside the container for production, because for dev local code is mounted in /project/src local volume but for production code needs to be build with image.

We can mount a local directory to a container volume by specifying filesystem binding. This is why we can reflect local changes in a running instance of a container when we run the service on our local environment.
In production, we want to remove this binding because we don't want our service capable of changes.

Volume is a logical drive (a drive space logically created on top of a physical hard disk drive. It's an acccessible, separate storage area with its own parameters and functions and operates independently)

two ways you can manage data in docker - data volumes and data volume containers

Main use cases for volumes
To keep data around when a container is removed
To share data between the host filesystem and the Docker container
To share data with other Docker containers

how to manage persistent data in docker? meaning, how to use persistent data in postgres

how does the filesystem work in Docker?
union file system - create read-only layers and a container creates a read-write layer on top of it.

what's the difference between a data volume and a mounted host directory? It seems docker creates a volume automatically

volume is good for saving data after restart of Docker container

let the docker-compose.yml be the production file and the override file be development, staging, etc. We'll just specify multiple docker compose files in the `docker-compose up` command (`docker-compose up -f`)

what if our containers specifies many services? - backend, a cache, a lb and a db? What if only one service needs to change? How can we rebuild the image and only deploy the container that change rather than the entire network of containers?

depending on where the variable is declared, it is given a higher or lower priority by Compose. Here’s the order, ranking from highest priority to lowest:
Compose file
Shell environment variables
Environment file
Dockerfile
Variable is not defined
