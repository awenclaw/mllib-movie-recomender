### How to build:
In project directory type:
```sh	
sbt package
```	
### How to start:

Go to spark bin directory and execute: 
1.) for default parameters:
```sh	
spark-submit --class com.awenclaw.mllib.movierecomender.Start --master local[*] mllib-movie-recomender-assembly-1.0.jar
```		
or 
2.) for explicit parameters: 
```sh	
 spark-submit --class com.awenclaw.mllib.movierecomender.Start --master local[*] mllib-movie-recomender-assembly-1.0.jar --inputDataFile /aw_movies/input_data/u1.test --outputModelDir /aw_movies/input_data/model --sizeOfTrainingDataSet 0.8 --alsNumOfRanks 5 --alsNumOfIterations 5 --alsLambda 0.01
```