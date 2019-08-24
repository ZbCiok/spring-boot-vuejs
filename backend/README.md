Database - h2
-------------

In <em>application.properties</em>:

I use h2 with path:
<em>spring.datasource.url=jdbc:h2:file:/home/zbyszek/military-equipment/h2db/h2db</em>.

<strong>You must use your own path</strong>, for example: 

<em>spring.datasource.url=jdbc:h2:file: /home/{YOUR PATH}/h2db/h2db</em>.
<br/><br/>
<em>/home</em> <strong>IS OBLIGATORY</strong>.
    

h2 console
----------

See:&nbsp;&nbsp;<em>WebConfiguration.java</em>

Calling:&nbsp;&nbsp;<em>http://localhost:8080/console</em>


Run spring boot:
----------------

<em>mvn spring-boot:run</em>

