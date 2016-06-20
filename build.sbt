name := "mllib-movie-recomender"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.6.0" % "provided",
  "org.apache.spark" %% "spark-mllib" % "1.6.0" % "provided",
  "com.github.scopt" %% "scopt" % "3.5.0"
)

//resolvers += Resolver.sonatypeRepo("public")


//assemblyOption in assembly :=
//  (assemblyOption in assembly).value.copy(includeScala = false)
//
//assemblyMergeStrategy in assembly := {
//  case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
//  case x =>
//    val oldStrategy = (assemblyMergeStrategy in assembly).value
//    oldStrategy(x)
//}



