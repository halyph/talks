# Demo

## Initial steps
Install boot2docker
Install docker
Pull Ubuntu image `docker pull ubuntu:14.04`
Run ubuntu container `docker run -it ubuntu:14:04`

## Dockerfile
Create 
```
FROM ubuntu:14.04
CMD echo "Hello ITRally"
```

Run boot2docker 
```
$ boot2docker up
Waiting for VM and Docker daemon to start...
..........ooo
Started.
Writing C:\Users\oivasiv\.boot2docker\certs\boot2docker-vm\ca.pem
Writing C:\Users\oivasiv\.boot2docker\certs\boot2docker-vm\cert.pem
Writing C:\Users\oivasiv\.boot2docker\certs\boot2docker-vm\key.pem

To connect the Docker client to the Docker daemon, please set:
    export DOCKER_TLS_VERIFY=1
    export DOCKER_HOST=tcp://192.168.59.103:2376
    export DOCKER_CERT_PATH='C:\Users\oivasiv\.boot2docker\certs\boot2docker-vm'

$ boot2docker ip
192.168.59.103
```

### docker images --tree
[Deprecate 'docker images --tree' and 'docker images --viz' #5001](https://github.com/docker/docker/pull/5001)
As discussed on #docker-dev, these flags can easily be implemented as external tools. We want to keep the Docker core as small as possible. Let's start cutting down the "nice-to-have" features.

The commands are no longer listed or documented.
The commands still work but print a deprecation warning.
The commands should be removed in a future version.

```
$ docker  images --tree
Warning: '--tree' is deprecated, it will be removed soon. See usage.
└─511136ea3c5a Virtual Size: 0 B
  ├─4f903438061c Virtual Size: 84.98 MB
  │ └─1265e16d0c28 Virtual Size: 84.98 MB
  │   └─0cbe7e43ed7f Virtual Size: 84.98 MB
  │     └─6d44bc059d9f Virtual Size: 85.04 MB
  │       └─176085a111eb Virtual Size: 85.04 MB
  │         └─1489fd109e85 Virtual Size: 85.04 MB
  │           └─19e4f320736e Virtual Size: 93.44 MB
  │             └─3f88b02f7d56 Virtual Size: 93.44 MB
  │               └─945d2e6cfe67 Virtual Size: 93.44 MB
  │                 └─2305983063a4 Virtual Size: 93.44 MB
  │                   └─89785ab09669 Virtual Size: 93.44 MB
  │                     └─224873bdcaa1 Virtual Size: 93.44 MB Tags: nginx:latest
  ├─f3c84ac3a053 Virtual Size: 188.1 MB
  │ └─a1a958a24818 Virtual Size: 188.3 MB
  │   └─9fec74352904 Virtual Size: 188.3 MB
  │     └─d0955f21bf24 Virtual Size: 188.3 MB Tags: ubuntu:14.04, ubuntu:14.04.2, ubuntu:latest, ubuntu:trusty, ubuntu:trusty-20150320
```



## Image vs dontainer
http://docs.docker.com/terms/image/
http://docs.docker.com/terms/layer/ 
http://docs.docker.com/terms/container/
