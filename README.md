Pong Scorebored
===============

For more information, visit http://pong-scorebored.blackchip.org

Building from source
--------------------

To run from the command line, type in:

```
ant jar
java -jar dist/pong-scorebored.jar
```

A Mac OS X application is also created in the dist directory.

Development is done using NetBeans 7 and can be opened directly as a
project. Ivy is used for dependency management--opening the project for the 
first time will complain about reference problems. Ignore the warning, close
the dialog box and build the project anyway. This will download all the 
required jar files in the lib directory. Once this is done, right click on the 
project name, click on "Resolve Reference Problems...", and close that window.
The project should now change from red to black.

There Be Dragons
----------------

Work was done on this application at warp 9. Therefore the code is ugly,
untestable, and difficult to navigate through. You have been warned.





