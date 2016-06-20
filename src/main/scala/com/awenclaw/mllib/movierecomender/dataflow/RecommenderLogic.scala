package com.awenclaw.mllib.movierecomender.dataflow

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD

/**
  * Created by a581275 on 2016-06-17.
  */
object RecommenderLogic {
  val INPUT_DATA_FILE_SEPARATOR = ";"

  implicit class InputDataFormatter(val rdd: RDD[String]){

    def formatInputData:RDD[Rating] = {
      rdd
        .map(_.split(INPUT_DATA_FILE_SEPARATOR))
        .filter(_.size == 4)
        .map(_ match {
          case Array(user, item, rate, time) => Rating(user.toInt, item.toInt, rate.toDouble)
        })
    }

  }

}
