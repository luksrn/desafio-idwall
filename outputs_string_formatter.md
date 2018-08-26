## Outras saídas do programa String formatter

```
$ java -jar string-formatter/target/StringFormatter-1.0-SNAPSHOT.jar "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters." 20
Inputs: 
Text: In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.
Limit: 20
Should justify: true
=========================
Output: 
In the beginning God
created  the heavens
and  the  earth. Now
the     earth    was
formless  and empty,
darkness   was  over
the  surface  of the
deep, and the Spirit
of  God was hovering
over    the  waters.
```


```
$ java -jar string-formatter/target/StringFormatter-1.0-SNAPSHOT.jar "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters." 20 false
Inputs: 
Text: In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.
Limit: 20
Should justify: false
=========================
Output: 
In the beginning God
created the heavens
and the earth. Now
the earth was
formless and empty,
darkness was over
the surface of the
deep, and the Spirit
of God was hovering
over the waters.
```