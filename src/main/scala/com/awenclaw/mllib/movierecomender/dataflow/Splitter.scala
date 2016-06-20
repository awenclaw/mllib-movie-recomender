package com.awenclaw.mllib.movierecomender.dataflow

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD

/**
  * Created by a581275 on 2016-06-17.
  */
case class Splitter(datasetToSplit:RDD[Rating], sizeOfTrainingDataSet:Double) {
  val splits = datasetToSplit.randomSplit(Array(sizeOfTrainingDataSet, 1 - sizeOfTrainingDataSet))
  val trainingDataSet = splits(0).cache()
  val testDataSet = splits(1).cache()
}
