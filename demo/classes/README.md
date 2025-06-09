

Create a circle jar and put inside the dist folder.
`javac -cp dist/circle.jar  Main.java`


`java Main`
1.0
1809.5573684677208
java.awt.Point[x=3,y=0]

Create Main.class to the out folder
` javac -d out Main.java`

Jar Main.class in the out folder to the dist folder, add a `manifest.txt` that points to the main. 
`jar cfm dist/main.jar manifest.txt -C out/ .`
Run the main.jar 
`java -jar dist/main.jar`

Expected output
1.0
1809.5573684677208
java.awt.Point[x=3,y=0]