[](http://stackoverflow.com/questions/16047306/how-is-docker-io-different-from-a-normal-virtual-machine)

Docker currently uses [LinuX Containers][1] (LXC), which run in the same operating system as its host. This allows it to share a lot of the host operating system resources. It also uses [AuFS][2] for the file system. It also manages the networking for you as well. 

AuFS is a layered file system, so you can have a read only part, and a write part, and merge those together. So you could have the common parts of the operating system as read only, which are shared amongst all of your containers, and then give each container its own mount for writing.

So let's say you have a container image that is 1GB in size. If you wanted to use a Full VM, you would need to have 1GB times x number of VMs you want. With LXC and AuFS you can share the bulk of the 1GB and if you have 1000 containers you still might only have a little over 1GB of space for the containers OS, assuming they are all running the same OS image.   

A full virtualized system gets its own set of resources allocated to it, and does minimal sharing. You get more isolation, but it is much heavier (requires more resources).

With LXC you get less isolation, but they are more lightweight and require less resources. So you could easily run 1000's on a host, and it doesn't even blink. Try doing that with Xen, and unless you have a really big host, I don't think it is possible.

A full virtualized system usually takes minutes to start, LXC containers take seconds, and sometimes even less than a second. 

There are pros and cons for each type of virtualized system. If you want full isolation with guaranteed resources, a full VM is the way to go. If you just want to isolate processes from each other and want to run a ton of them on a reasonably sized host, then LXC might be the way to go.

For more information, check out [this set of blog posts][3] which do a good job of explaining how LXC works.

> I feel foolish for asking, but why is deploying software to a docker image (if that's the right term) easier than simply deploying to a consistent production environment?

Deploying a consistent production environment is easier said than done. Even if you use tools like chef and puppet, there are always OS updates and other things that change between hosts and environments. 

What docker does is it gives you the ability to snapshot the OS into a common image, and makes it easy to deploy on other docker hosts. Locally, dev, qa, prod, etc, all the same image. Sure you can do this with other tools, but not as easily or fast. 

This is great for unit testing, lets say you have 1000 tests and they need to connect to a database, and in order to not break anything you need to run serially so that the tests don't step on each other (run each test in a transaction and roll back). With Docker you could create an image of your database, and then run all the tests in parallel since you know they will all be running against the same snapshot of the database. Since they are running in parallel and in LXC containers they could run all on the same box at the same time, and your tests will finish much faster. Try doing that with a full VM.

**Edit:** From comments...
> Interesting! I suppose I'm still confused by the notion of "snapshot[ting] the OS". How does one do that without, well, making an image of the OS?

Well, let's see if I can explain. You start with a base image, and then make your changes, and commit those changes using docker, and it creates an image. This image contains only the differences from the base. When you want to run your image, you also need the base, and it layers your image on top of the base using a layered file system, in this case AUFS. AUFS merges the different layers together and you get what you want, and you just need to run it. You can keep adding more and more images (layers) and it will keep only saving the diffs. Since docker typically builds on top of ready-made images from a [registry][4], you rarely have to "snapshot" the whole OS yourself.


  [1]: http://lxc.sourceforge.net/
  [2]: http://aufs.sourceforge.net/
  [3]: http://blog.dotcloud.com/under-the-hood-linux-kernels-on-dotcloud-part
  [4]: https://registry.hub.docker.com/