# DOCKER_RUN_ALPINE has been added for creating .env file because
#		- trying to run a docker-compose command will fail if .env is not there
#				as service musketeers requires it
DOCKER_RUN_ALPINE = docker run -v $(PWD):/opt/app -w /opt/app alpine

APP_NAME = hello-world
VERSION = 0.0.1
IMAGE_NAME ?= aochsner/$(APP_NAME):$(VERSION)
COMPOSE_RUN_JAVA = docker-compose run --rm java
COMPOSE_RUN_MUSKETEERS = docker-compose run --rm musketeers
# ENVFILE is .env.template by default but can be overwritten
ENVFILE ?= .env.template

# all is the default Make target. it tests, and builds the application and cleans everything.
all:
	ENVFILE=.env.example $(MAKE) envfile test build dockerBuild clean
.PHONY: all

############
# env file #
############

# envfile creates or overwrites .env with $(ENVFILE)
envfile:
	$(DOCKER_RUN_ALPINE) sh -c 'cp -f $(ENVFILE) .env'
.PHONY: envfile

removeEnvfile:
	$(DOCKER_RUN_ALPINE) sh -c 'rm -f .env'
.PHONY: removeEnvfile

##################
# Public Targets #
##################

# test tests the application
test:
	$(COMPOSE_RUN_JAVA) make _test
.PHONY: test

build:
	$(COMPOSE_RUN_JAVA) make _build
.PHONY: build

run:
	$(COMPOSE_RUN_JAVA) make _run
.PHONY: run

shell:
	$(COMPOSE_RUN_JAVA) bash
.PHONY: shell

# clean removes build artifacts and cleans docker environment
clean:
	$(COMPOSE_RUN_JAVA) make _clean
	docker-compose down --remove-orphans
	-$(MAKE) dockerRemove removeEnvfile
.PHONY: clean

dockerBuild:
	docker build --no-cache -t $(IMAGE_NAME) .
.PHONY: dockerBuild

dockerRun:
	docker run --env-file $(ENVFILE) -p 8080:8080 --rm $(IMAGE_NAME)
.PHONY: dockerRun

dockerRemove:
	docker rmi -f $(IMAGE_NAME)
.PHONY: dockerRemove

###################
# Private Targets #
###################

_test:
	./gradlew check --no-daemon
.PHONY: _test

_build:
	./gradlew assemble --no-daemon
.PHONY: _build

_run:
	./gradlew bootRun --no-daemon
.PHONY: _run

_clean:
	./gradlew clean --no-daemon
.PHONY: _clean

