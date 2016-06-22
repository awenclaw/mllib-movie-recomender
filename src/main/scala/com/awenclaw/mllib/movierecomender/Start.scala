package com.awenclaw.mllib.movierecomender

import com.awenclaw.mllib.movierecomender.dataflow.Splitter
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.ALS
import com.awenclaw.mllib.movierecomender.dataflow.RecommenderLogic._
import com.awenclaw.mllib.movierecomender.modelEvaluator.ModelEvaluator._
import scopt.OptionParser


object Start {

  case class Params(
    inputDataFile:String = "/aw_movies/input_data/u1.test",
    outputModelDir:String = "/aw_movies/input_data/model",
    sizeOfTrainingDataSet:Double = 0.8,
    alsNumOfRanks:Int = 5,
    alsNumOfIterations:Int = 20,
    alsLambda:Double = 0.01
  )

  def main(args: Array[String]) {
    val defaultParams = Params()
    val parser = new OptionParser[Params]("Start") {
      opt[String]("inputDataFile").action((x, c) => c.copy(inputDataFile = x))
      opt[String]("outputModelDir").action((x, c) => c.copy(outputModelDir = x))
      opt[Double]("sizeOfTrainingDataSet").action((x, c) => c.copy(sizeOfTrainingDataSet = x))
      opt[Int]("alsNumOfRanks").action((x, c) => c.copy(alsNumOfRanks = x))
      opt[Int]("alsNumOfIterations").action((x, c) => c.copy(alsNumOfIterations = x))
      opt[Double]("alsLambda").action((x, c) => c.copy(alsLambda = x))
    }
    parser.parse(args, defaultParams).map{
      params => run(params)
    } getOrElse{
      System.exit(1)
    }
  }



  def run(params: Params) ={
    val conf = new SparkConf().setAppName(s"Movie recommender app with params: $params")
    val sc = new SparkContext(conf)

    val inputData = sc.textFile(params.inputDataFile)

    val movieRatings = inputData.formatInputData
    val splitter  = Splitter(movieRatings, params.sizeOfTrainingDataSet)

    val model = ALS.train(
      splitter.trainingDataSet,
      params.alsNumOfRanks,
      params.alsNumOfIterations,
      params.alsLambda
    )
    model.save(sc, params.outputModelDir)

    val RMSE = model.calculateRMSE(splitter.testDataSet, splitter.trainingDataSet)
    println("ROOT MEAN SQUERE ERROR: " + RMSE)

    sc.stop()
  }
}
