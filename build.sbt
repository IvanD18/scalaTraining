name := "Area"
 
version := "1.0" 
      
lazy val `area` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( jdbc , caffeine , ws , specs2 % Test , guice)

      