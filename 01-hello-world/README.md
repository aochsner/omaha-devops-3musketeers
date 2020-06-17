# 01-hello-world

## Prerequisites

- [Docker](https://www.docker.com/)
- [Compose](https://docs.docker.com/compose/)
- [Make](https://www.gnu.org/software/make/)

## Usage

```bash
$ ENVFILE=.env.example make envfile
$ make echo
docker-compose run --rm musketeers make _echo
echo Hello, World!
Hello, World!
```

For more information, visit [3musketeers.io][link3Musketeers].
