# OJ system

## Developer

 
 

 

## Environment preparation

 To run this program, you must have **Ubuntu** system,

 Linux kernel version >= 3.10

 jdk1.8 version,

 mysql8.0,

 redis.

### go-judge system operation

**The port required by gojudge is 5050**

<a href="">go_judge handbook</a>.

  1. Download the binary precompiled file

https://github.com/criyle/go-judge/releases

or run on Docker

~~~bash
docker run -it --rm --privileged --shm-size=256m -p 5050:5050 criyle/executorserver
~~~

**Run binary precompiled files on Ubuntu**

First set permissions

~~~bash
chmod +x filename
~~~

After that start sandbox

~~~BASH
./file name  
~~~

#### c/c++ locale test

**Next we open postman for testing and send the request in post form http://IP:5050/run with parameters**

~~~java
{
    "cmd": [{
        "args": ["/usr/bin/g++", "Main.cc", "-o", "a"],
        "env": ["PATH=/usr/bin:/bin"],
        "files": [{
            "content": ""
        }, {
            "name": "stdout",
            "max": 10240
        }, {
            "name": "stderr",
            "max": 10240
        }],
        "cpuLimit": 10000000000,
        "memoryLimit": 104857600,
        "procLimit": 50,
        "copyIn": {
            "Main.cc": {
                "content": "#include <iostream>\nusing namespace std;\nint main() {\nint a, b;\ncin >> a >> b;\ncout << a + b << endl;\n} "
            }
        },
        "copyOut": ["stdout", "stderr"],
        "copyOutCached": ["Main.cc", "a"],
        "copyOutDir": "1"
    }]
}
~~~

**Our server will return us**

~~~bash
[
{
"status": "Accepted",
"exitStatus": 0,
"time": 726910000,
"memory": 55812096,
"runTime": 787566071,
"files": {
"stderr": "",
"stdout": ""
},
"fileIds": {
"Main.cc": "4EK46KIB",
"a": "LR567VHA"
}
}
]
~~~

**Next we run him according to the Id of Main**

~~~bash
{
    "cmd": [{
        "args": ["a"],
        "env": ["PATH=/usr/bin:/bin"],
        "files": [{
            "content": "1 1"
        }, {
            "name": "stdout",
            "max": 10240
        }, {
            "name": "stderr",
            "max": 10240
        }],
        "cpuLimit": 10000000000,
        "memoryLimit": 104857600,
        "procLimit": 50,
        "strictMemoryLimit": false,
        "copyIn": {
            "a": {
                "fileId": "LR567VHA"
            }
        }
    }]
}
~~~

**Server feedback to us**

~~~bash
[
{
"status": "Accepted",
"exitStatus": 0,
"time": 7191000,
"memory": 6672384,
"runTime": 15512983,
"files": {
"stderr": "",
"stdout": "2\n"
}
}
]
~~~

#### java locale test

todo

#### Awesome goLang language test

#### python3 language test

#### python2 language test

## backend startup

todo

## front-end startup



## Dev log



## Architecture diagram

## Thanks

 Thanks to Mr. Zhang Yijia, thanks to all the members of the TML TEAM team for the cooperative development, thanks to all the people who helped us develop, and thanks to Haer Rongbin Guangsha College
