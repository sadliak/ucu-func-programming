scalaVersion := "2.12.12"
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3"
libraryDependencies += "com.twitter" %% "util-eval" % "6.43.0"
