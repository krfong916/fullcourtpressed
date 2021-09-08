I am proud of your grit that you displayed. To continue to ask questions and search for the best answer after failing many times over is commendable - something to hang your hat on.
The bad, we should reflect on 100+ commits. That is something we should be critical of. I'd like to see us pay attention to our focus when we are grinding out the problem. Evident by the 100+ commits, we didn't have the patience to stay present and pay attention to detail, particularly when our tests started failing. In order to improve, I'd like to us read documentation closely and think slowly.

# Technology

- Travis runs jobs _in parallel_.
- Docker creates a database image and creates a file system (volume) on first compile. It is at this time and only this time that database image configuration, such as user, password, name, port, and host, can be made.
- Connection pooling is a great way for reducing the cost of DNS resolution and TCP/IP connection creation

Dev env
we have two dev environments:

- running dev env using docker-compose
  - we use this option when we would like to create a new db image
- running dev env from host (local env)
  - we use this option when we need to do feature development and testing
  - using a host env for testing is significantly more performant than using docker-compose container to container.
    - Jest `watch` does not work without a `.git` directory. Our docker iamges excludes our `.git` directory. Instead, we resort to using `watchAll`. This Jest configuration runs _all_ tests every test change. Secondly, Docker uses significant CPU. In order to detect file changes, Jest crawls every file in our working directory - this includes node_modules.

CI env

- CI env is in Travis. Travis runs our jobs in parallel - test and deploy. We use docker-compose to build and run our CI env. We test our application using `npm run test`.

Staging env

- We use Heroku for our staging environment. When our integration tests pass, Travis performs two deploys: a deploy of our application to Heroku's container registry, another deploy to our Heroku server postgres instance. Heroku manages the runtime of our application. When we deploy, Heroku triggers a build and execution of our `Dockerfile` (and not `docker-compose`), and it also erases and seeds our database instances.

Env variables
We use env variables to make our application runnable across multiple environments: local environment, CI service, and cloud providers. We have a few 3rd party dependencies, namely Postgres, Sendgrid, and Redis atm. We specify dependency secrets in our `env` files and on the platforms we use. To ensure security, we remove all references to our secrets from logs and in our application.
