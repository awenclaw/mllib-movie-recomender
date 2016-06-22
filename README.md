### Prerequisites:

* scala 2.10.4 or higher
* sbt (scala build tool)
* Spark 1.6.0 on Hadoop cluster

### How to build:
In project directory type:
```sh	
sbt assembly
```	
### How to start:

Go to spark bin directory and execute: <br>
1.) for default parameters:
```sh	
spark-submit --class com.awenclaw.mllib.movierecomender.Start --master local[*] mllib-movie-recomender-assembly-1.0.jar
```		
or <br>
2.) for explicit parameters: 
```sh	
 spark-submit --class com.awenclaw.mllib.movierecomender.Start --master local[*] mllib-movie-recomender-assembly-1.0.jar --inputDataFile /aw_movies/input_data/u1.test --outputModelDir /aw_movies/input_data/model --sizeOfTrainingDataSet 0.8 --alsRank 5 --alsNumOfIterations 5 --alsLambda 0.01
```
file u1.test is one of files in MovieLens dataset: http://grouplens.org/datasets/movielens/